package com.teampome.pome.data.remote.response

data class ResponseAuth(
    val type: String,
    val id: Int,
    val uuid: String?,
    val accessToken: String,
    val refreshToken: String
)

