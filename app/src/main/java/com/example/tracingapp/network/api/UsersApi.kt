package com.example.tracingapp.network.api

import com.example.tracingapp.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface UsersApi {
    @POST("users")
    fun register(@Body details: UserDetails): Call<User>

    @PUT("users/login")
    fun login(@Body details: Login): Call<User>

    @PUT("users/logout")
    fun logout(): Call<User>

    @PUT("users/details")
    fun updateUserDetails(@Body details: UpdateDetails): Call<User>

    @GET("users/details")
    fun getOwnDetails(): Call<User>
}