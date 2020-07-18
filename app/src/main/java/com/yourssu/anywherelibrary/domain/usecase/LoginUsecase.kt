package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LoginService
import com.yourssu.anywherelibrary.data.model.SigninRequest
import com.yourssu.anywherelibrary.data.model.SigninResponse
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginUsecase {
    private val loginService : LoginService = RetrofitManager.create(LoginService::class.java)

    fun doLogin(identification : String ,hassedPassword : String) : Single<SigninResponse> {
        val request : SigninRequest = SigninRequest(identification, hassedPassword)
        return loginService.doLogin(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}