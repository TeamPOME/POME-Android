package com.teampome.pome.data.remote.response

data class ResponseFriendsProflie(
    val id: Int,
    val userId: Int,
    val targetId: Int,
    val createdAt: String,
    val updatedAt: String
)