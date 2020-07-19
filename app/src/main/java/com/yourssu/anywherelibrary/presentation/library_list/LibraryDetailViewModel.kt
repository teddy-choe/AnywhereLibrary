package com.yourssu.anywherelibrary.presentation.library_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yourssu.anywherelibrary.domain.entity.SimpleSeat
import com.yourssu.anywherelibrary.domain.usecase.GetDetailLibraryUsecase
import com.yourssu.anywherelibrary.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LibraryDetailViewModel(
    private val getDetailLibraryUsecase: GetDetailLibraryUsecase = GetDetailLibraryUsecase()
): ViewModel() {
    val getDetailSuccess : SingleLiveEvent<Void> = SingleLiveEvent()
    var seatList : ArrayList<SimpleSeat> = ArrayList()
    var totalCount : Int = 0

    fun getDetailLibrary(libraryId : Long) {
        getDetailLibraryUsecase.getDetailLibrary(libraryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.library.seats != null) {
                    seatList = it.library.seats
                    totalCount = it.library.totalPersonnel
                    getDetailSuccess.call()
                }
            }, {
                Log.d("Detail", it.localizedMessage)
            })
    }
}