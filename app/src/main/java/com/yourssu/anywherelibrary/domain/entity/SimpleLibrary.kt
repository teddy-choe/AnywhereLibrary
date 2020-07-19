package com.yourssu.anywherelibrary.domain.entity

data class SimpleLibrary (
    val hangout: String,
    val id: Long,
    val name: String,
    val owner: SimpleUser?,
    val seats: ArrayList<SimpleSeat>?,
    val totalPersonnel: Int
)