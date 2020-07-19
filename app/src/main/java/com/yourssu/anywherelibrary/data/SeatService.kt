package com.yourssu.anywherelibrary.data

import com.yourssu.anywherelibrary.data.model.*
import io.reactivex.Single
import retrofit2.http.*

interface SeatService {
    @PATCH("/v1/api/libraries/{libraryId}/seats/{seatId}")
    fun updateSeatTime(@Path("libraryId") libraryId: Long,
                      @Path("seatId") seatId: Long,
                      @Body updateSeatTimeRequest: UpdateSeatTimeRequest) : Single<ChooseSeatResponse>

    @POST("/v1/api/libraries/{libraryId}/seats/seat")
    fun selectSeat(@Header("accessToken") accessToken : String,
                   @Path("libraryId") libraryId : Long) : Single<ChooseSeatResponse>

    @HTTP(method="DELETE", hasBody=true, path="/v1/api/libraries/{libraryId}/seats/{seatId}")
    //@DELETE("/v1/api/libraries/{libraryId}/seats/{seatId}")
    fun deleteSeat(@Header("accessToken") accessToken : String,
                   @Path("libraryId") libraryId : Long,
                   @Path("seatId") seatId: Long,
                   @Body updateSeatTimeRequest: UpdateSeatTimeRequest) : Single<String>
}