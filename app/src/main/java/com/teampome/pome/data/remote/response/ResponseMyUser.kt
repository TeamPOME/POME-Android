package com.teampome.pome.data.remote.response

data class ResponseMyUser(
    val nickname: String,
    val profileImage: String,
    val badge: Level,
    val goalStorageCount: Int
) {
    data class Level(
        val recordLevel: Int,
        val reactLevel: Int,
        val growLevel: Int,
        val frankLevel: Int
    )
}


