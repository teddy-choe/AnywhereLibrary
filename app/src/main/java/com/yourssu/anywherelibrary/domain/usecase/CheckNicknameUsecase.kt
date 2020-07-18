package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LoginService
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckNicknameUsecase {
    private val loginService : LoginService = RetrofitManager.create(LoginService::class.java)

    fun checkNickname(nickname : String) : Single<String> {
        return loginService.checkNickname(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}