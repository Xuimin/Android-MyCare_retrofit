package com.example.tracingapp.data.model

import com.google.gson.annotations.SerializedName

data class UpdateDetails(
    @SerializedName("fullname")
    val fullname: String = "",

    @SerializedName("ic")
    val ic: String = "",

    @SerializedName("location")
    val location: String = "",

    @SerializedName("password")
    val password: String = "",

    @SerializedName("confirmPassword")
    val confirmPassword: String = "",
)
