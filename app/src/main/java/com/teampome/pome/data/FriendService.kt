package com.teampome.pome.data

import com.teampome.pome.data.remote.request.RequestFriendAddReaction
import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseFriendReaction
import com.teampome.pome.data.remote.response.ResponseFriendsAll
import com.teampome.pome.data.remote.response.ResponseFriendsProflie
import retrofit2.http.*

interface FriendService {
    @GET("friends")
    suspend fun getAllFriends(): BaseResponse<List<ResponseFriendsProflie>>

    @GET("friends/records")
    suspend fun getFriendsRecords(@Query("userId")userId:Int):BaseResponse<List<ResponseFriendsAll>>

    @POST("records/reaction")
    suspend fun setFriendsReaction(
        @Body body: RequestFriendAddReaction
    ):BaseResponse<Unit>

    @GET("records/{recordId}/reaction")
    suspend fun getFriendsReaction(
        @Path("recordId")recordId:Int,
        @Query("type")type:Int):BaseResponse<ResponseFriendReaction>
}