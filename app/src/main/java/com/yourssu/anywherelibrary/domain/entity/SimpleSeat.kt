package com.yourssu.anywherelibrary.domain.entity

data class SimpleSeat (
    val id: Long,
    val learningTime: String,
    val restTime: String,
    val user: SimpleUser
)