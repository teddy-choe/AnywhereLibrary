package com.yourssu.anywherelibrary.domain.usecase

import com.yourssu.anywherelibrary.data.SeatService
import com.yourssu.anywherelibrary.data.model.ChooseSeatResponse
import com.yourssu.anywherelibrary.util.RetrofitManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SelectSeatUsecase {
    private val seatService: SeatService = RetrofitManager.create(SeatService::class.java)

    fun selectSeat(accessToken: String, libraryId: Long): Single<ChooseSeatResponse> {
        return seatService.selectSeat(accessToken, libraryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}