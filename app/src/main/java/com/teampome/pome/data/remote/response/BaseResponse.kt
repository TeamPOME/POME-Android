package com.teampome.pome.data.remote.response

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T?
)
