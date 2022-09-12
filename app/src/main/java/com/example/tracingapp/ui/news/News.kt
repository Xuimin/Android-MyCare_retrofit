package com.example.tracingapp.ui.news

import androidx.annotation.DrawableRes

data class News(
    var id: Int,
    @DrawableRes
    var image: Int,
    var title: String,
    var description: String,
    var date: String
)
