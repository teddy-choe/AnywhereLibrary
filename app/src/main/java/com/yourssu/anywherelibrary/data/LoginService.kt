package com.yourssu.anywherelibrary.data

import com.yourssu.anywherelibrary.data.model.SigninRequest
import com.yourssu.anywherelibrary.data.model.SigninResponse
import com.yourssu.anywherelibrary.data.model.SignupRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {
    @POST("/v1/api/users/user/sign-in")
    fun doLogin(@Body signinRequest : SigninRequest) : Single<SigninResponse>

    @POST("/v1/api/users/user/sign-up")
    fun doSignup(@Body signupRequest: SignupRequest) : Single<SigninResponse>

    @GET("/v1/api/users/user/verification/identification/{identification}")
    fun checkIdentification(@Path("identification") identification : String) : Single<String>

    @GET("/v1/api/users/user/verification/nickname/{nickname}")
    fun checkNickname(@Path("nickname") nickname: String) : Single<String>
}