package com.yourssu.anywherelibrary.presentation.library_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yourssu.anywherelibrary.domain.usecase.DeleteSeatUsecase
import com.yourssu.anywherelibrary.util.ConstValue.CONST_ACCESS_TOKEN
import com.yourssu.anywherelibrary.util.SharedPreferenceManager
import com.yourssu.anywherelibrary.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SeatViewModel(
    private val deleteSeatUsecase : DeleteSeatUsecase = DeleteSeatUsecase()
): ViewModel() {
    val deleteSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    fun deleteSeat(learningTime : String, breakTime : String, libraryId : Long, seatId : Long) {
        deleteSeatUsecase.deleteSeat(SharedPreferenceManager.getStringPref(
                        CONST_ACCESS_TOKEN), libraryId, seatId, learningTime, breakTime)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Log.d("Detail", it.toString())
                        }, {
                            deleteSuccess.call()
                            Log.d("Detail", it.localizedMessage)
                        })
    }
}