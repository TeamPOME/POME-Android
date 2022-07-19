package com.teampome.pome.data

import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseFriendsAll
import retrofit2.http.GET

interface FriendService {
    @GET("friends")
    suspend fun getAllFriends(): BaseResponse<List<ResponseFriendsAll>>

    @GET("friends/records")
    suspend fun getFriendsRecords():BaseResponse<List<ResponseFriendsAll>>
}