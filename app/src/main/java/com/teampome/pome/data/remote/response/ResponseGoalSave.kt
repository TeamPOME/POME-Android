package com.teampome.pome.data.remote.response

data class ResponseGoalSave(
    val id: Int,
    val message: String,
    val amount: Int,
    val payAmount: Int,
    val rate: Int,
    val isPublic: Boolean,
    val isSuccess: Boolean
)
