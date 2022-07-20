package com.teampome.pome.data.remote.request

data class RequestGoalCreate(
    val startDate: String,
    val endDate: String,
    val category: String,
    val message: String,
    val amount: Int,
    val isPublic: Boolean
)
