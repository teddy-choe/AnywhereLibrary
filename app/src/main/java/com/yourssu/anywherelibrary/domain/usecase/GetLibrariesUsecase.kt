package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LibraryService
import com.yourssu.anywherelibrary.data.model.SearchLibrariesResponse
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetLibrariesUsecase {
    private val libraryService : LibraryService = RetrofitManager.create(LibraryService::class.java)

    fun getLibraries(currentPage : Int ,size : Int) : Single<SearchLibrariesResponse> {
        return libraryService.searchLibrary(currentPage, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}