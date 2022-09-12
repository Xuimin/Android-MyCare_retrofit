package com.example.tracingapp.ui.menu.faq

data class Faq(
    val id: Int,
    val question: String,
    val answer: String,
    var expandable: Boolean = false
)
