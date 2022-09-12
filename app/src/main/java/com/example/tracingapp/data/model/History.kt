package com.example.tracingapp.data.model

import com.google.gson.annotations.SerializedName

data class History (
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("location")
    val location: String = "",

    @SerializedName("date")
    val date: String = "",

    @SerializedName("time")
    val time: String = "",

    @SerializedName("userId")
    val userId: String = ""
)