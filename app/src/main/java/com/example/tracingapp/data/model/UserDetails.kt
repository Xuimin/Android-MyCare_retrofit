package com.example.tracingapp.data.model

import com.google.gson.annotations.SerializedName

data class UserDetails (
    @SerializedName("fullname")
    val fullname: String = "",

    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("location")
    val location: String = "",

    @SerializedName("password")
    val password: String = "",

    @SerializedName("confirmPassword")
    val confirmPassword: String = "",
)