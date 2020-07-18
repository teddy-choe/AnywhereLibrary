package com.yourssu.anywherelibrary.data.model

import com.yourssu.anywherelibrary.domain.entity.SimpleLibrary

data class SearchLibrariesResponse (
    val currentPage: Int,
    val libraries: ArrayList<SimpleLibrary>,
    val totalLibraries: Int,
    val totalPage: Int
)