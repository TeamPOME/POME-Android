package com.teampome.pome.data.remote.response

data class ResponseRecordsLook(
    val date: String,
    val amount: Int,
    val content: String,
    val startEmotion: Int,
    val endEmotion: Int
)
