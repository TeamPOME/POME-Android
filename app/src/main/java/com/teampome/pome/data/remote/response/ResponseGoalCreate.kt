package com.teampome.pome.data.remote.response

data class ResponseGoalCreate(
    val id: Int,
    val category: String,
    val message: String,
    val amount: Int,
    val startDate: String,
    val endDate: String,
    val isPublic: Boolean
)
