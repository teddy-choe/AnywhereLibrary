package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LoginService
import com.yourssu.anywherelibrary.data.model.SigninResponse
import com.yourssu.anywherelibrary.data.model.SignupRequest
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupUsecase {
    private val signupService: LoginService = RetrofitManager.create(LoginService::class.java)

    fun doSignup(hassedPassword: String, identification: String, nickname: String, universityName: String): Single<SigninResponse> {
        val request: SignupRequest = SignupRequest(hassedPassword, identification, nickname, universityName)
        return signupService.doSignup(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}