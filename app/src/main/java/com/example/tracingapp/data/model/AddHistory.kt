package com.example.tracingapp.data.model

import com.google.gson.annotations.SerializedName

data class AddHistory(
    @SerializedName("location")
    val location: String = "",

    @SerializedName("date")
    val date: String = "",

    @SerializedName("time")
    val time: String = ""
)
