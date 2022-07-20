package com.teampome.pome.data.remote.request

data class RequestRecordsCreate(
    val goalId: Int,
    val date: String,
    val amount: Int,
    val content: String,
    val startEmotion: Int
)
