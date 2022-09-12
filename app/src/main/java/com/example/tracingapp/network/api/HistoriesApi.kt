package com.example.tracingapp.network.api

import com.example.tracingapp.data.model.AddHistory
import com.example.tracingapp.data.model.History
import retrofit2.Call
import retrofit2.http.*

interface HistoriesApi {
    @GET("histories")
    fun getOwnHistory(): Call<List<History>>

    @POST("histories")
    fun checkIn(@Body details: AddHistory): Call<History>
}