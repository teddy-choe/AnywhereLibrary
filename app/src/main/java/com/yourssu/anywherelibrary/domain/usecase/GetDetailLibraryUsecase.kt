package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LibraryService
import com.yourssu.anywherelibrary.data.model.GetLibraryResponse
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetDetailLibraryUsecase {
    private val libraryService : LibraryService = RetrofitManager.create(LibraryService::class.java)

    fun getDetailLibrary(libraryId : Long) : Single<GetLibraryResponse> {
        return libraryService.getDetailLibrary(libraryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}