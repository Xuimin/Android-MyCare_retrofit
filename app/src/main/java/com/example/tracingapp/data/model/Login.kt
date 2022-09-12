package com.example.tracingapp.data.model

import com.google.gson.annotations.SerializedName

data class Login (
    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("password")
    val password: String = "",
)
