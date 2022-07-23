package com.teampome.pome.data.remote.response

data class RecordsBack(
    val id: Int,
    val date: String,
    val amount: Int,
    val content: String,
    val startEmotion: Int,
    val endEmotion: Int
)
