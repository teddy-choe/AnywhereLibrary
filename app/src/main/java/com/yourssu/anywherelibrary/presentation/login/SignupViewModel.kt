package com.yourssu.anywherelibrary.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yourssu.anywherelibrary.domain.usecase.SignupUsecase
import com.yourssu.anywherelibrary.util.ConstValue
import com.yourssu.anywherelibrary.util.SharedPreferenceManager
import com.yourssu.anywherelibrary.util.SingleLiveEvent
import com.yourssu.anywherelibrary.util.sha256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupViewModel: ViewModel() {
    private val signupUsecase: SignupUsecase = SignupUsecase()
    val signupSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    fun doSignup(id : String, password : String, nickname : String, university: String) {
        val hashedPassword = password.sha256()
        signupUsecase.doSignup(hashedPassword, id, nickname, university)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                SharedPreferenceManager.setPref(ConstValue.CONST_ACCESS_TOKEN, it.accessToken.token)
                SharedPreferenceManager.setPref(ConstValue.CONST_USER_ID, it.simpleUser.id)
                signupSuccess.call()
            }, {
                Log.d("MyTag", it.localizedMessage)
                signupSuccess.call()
            })
    }
}