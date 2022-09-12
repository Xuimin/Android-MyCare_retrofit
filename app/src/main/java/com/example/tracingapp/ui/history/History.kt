package com.example.tracingapp.ui.history

import androidx.annotation.DrawableRes

data class History(
    var id: Int,
    @DrawableRes
    var icon: Int,
    var name: String,
    var location: String,
    var date: String,
    var time: String
)
