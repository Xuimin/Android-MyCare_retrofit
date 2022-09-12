package com.example.tracingapp.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("fullname")
    val fullname: String = "",

    @SerializedName("password")
    val password: String = "",

    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("ic")
    val ic: String = "",

    @SerializedName("location")
    val location: String = "",

    @SerializedName("vaccine")
    val vaccine: String = "",

    @SerializedName("isVerified")
    val isVerified: Boolean? = null,

    @SerializedName("isActive")
    val isActive: Boolean? = null
)