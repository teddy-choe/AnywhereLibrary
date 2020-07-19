package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.LibraryService
import com.yourssu.anywherelibrary.data.model.CreateLibraryRequest
import com.yourssu.anywherelibrary.data.model.GetLibraryResponse
import com.yourssu.anywherelibrary.util.ConstValue.CONST_ACCESS_TOKEN
import com.yourssu.anywherelibrary.util.RetrofitManager
import com.yourssu.anywherelibrary.util.SharedPreferenceManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostLibraryUsecase {
    private val libraryService : LibraryService = RetrofitManager.create(LibraryService::class.java)

    fun postLibrary(hangout : String, name: String, totalPersonnel: Int) : Single<GetLibraryResponse> {
        val createLibraryRequest = CreateLibraryRequest(hangout, name, totalPersonnel)
        val token = SharedPreferenceManager.getStringPref(CONST_ACCESS_TOKEN)
        return libraryService.postLibrary(token, createLibraryRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}