package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.SeatService
import com.yourssu.anywherelibrary.data.model.ChooseSeatResponse
import com.yourssu.anywherelibrary.data.model.UpdateSeatTimeRequest
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpdateSeatTimeUsecase {
    private val seatService: SeatService = RetrofitManager.create(SeatService::class.java)

    fun updateSeatTime(libraryId: Long, seatId: Long, learningTime: String, restTime: String): Single<ChooseSeatResponse> {
        val updateSeatTimeRequest = UpdateSeatTimeRequest(learningTime, restTime)
        return seatService.updateSeatTime(libraryId, seatId, updateSeatTimeRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}