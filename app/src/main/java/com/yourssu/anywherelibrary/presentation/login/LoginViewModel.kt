package com.yourssu.anywherelibrary.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yourssu.anywherelibrary.domain.usecase.LoginUsecase
import com.yourssu.anywherelibrary.util.ConstValue
import com.yourssu.anywherelibrary.util.SharedPreferenceManager
import com.yourssu.anywherelibrary.util.SingleLiveEvent
import com.yourssu.anywherelibrary.util.sha256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    private val loginUsecase: LoginUsecase = LoginUsecase()
    val loginSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doLogin(id : String, password : String) {
        val hashedPassword = password.sha256()
        loginUsecase.doLogin(id, hashedPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                SharedPreferenceManager.setPref(ConstValue.CONST_ACCESS_TOKEN, it.accessToken.token)
                SharedPreferenceManager.setPref(ConstValue.CONST_USER_ID, it.simpleUser.id)
                loginSuccess.call()
            }, {
                Log.d("MyTag", it.localizedMessage)
            })
    }
}