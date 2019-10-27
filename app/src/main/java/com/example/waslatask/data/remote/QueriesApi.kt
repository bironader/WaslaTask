package com.example.waslatask.data.remote

import com.example.waslatask.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface QueriesApi {

    @GET("complete/search")
    fun getQuires(
        @Query("client") client: String = Constants.CLIENT
        , @Query("q") query: String
        , @Query("hl") language: String = Constants.LANGUAGE
    )
}