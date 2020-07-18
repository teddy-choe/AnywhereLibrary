package com.yourssu.anywherelibrary.data

import com.yourssu.anywherelibrary.data.model.CreateLibraryRequest
import com.yourssu.anywherelibrary.data.model.GetLibraryResponse
import com.yourssu.anywherelibrary.data.model.SearchLibrariesResponse
import io.reactivex.Single
import retrofit2.http.*

interface LibraryService {
    @GET("/v1/api/libraries")
    fun searchLibrary(@Query("currentPage") currentPage: Int,
                       @Query("size") size: Int) : Single<SearchLibrariesResponse>

    @GET("/v1/api/libraries/{libraryId}")
    fun getDetailLibrary(@Path("libraryId") libraryId : Long) : Single<GetLibraryResponse>

    @POST("/v1/api/libraries/library")
    fun postLibrary(@Header("accessToken") accessToken : String,
                    @Body createLibraryRequest : CreateLibraryRequest
    ) : Single<GetLibraryResponse>
}