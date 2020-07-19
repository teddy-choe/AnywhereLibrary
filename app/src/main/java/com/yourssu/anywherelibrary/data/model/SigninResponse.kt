package com.yourssu.anywherelibrary.data.model

import com.yourssu.anywherelibrary.domain.entity.AccessToken
import com.yourssu.anywherelibrary.domain.entity.SimpleUser

data class SigninResponse (
    val accessToken: AccessToken,
    val refreshToken : String,
    val simpleUser: SimpleUser
)