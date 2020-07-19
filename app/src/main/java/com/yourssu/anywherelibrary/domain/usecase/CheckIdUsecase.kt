package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LoginService
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckIdUsecase {
    private val loginService : LoginService = RetrofitManager.create(LoginService::class.java)

    fun checkId(identification : String) : Single<String> {
        return loginService.checkIdentification(identification)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}