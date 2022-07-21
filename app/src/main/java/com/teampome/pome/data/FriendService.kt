package com.teampome.pome.data

import com.teampome.pome.data.remote.request.RequestFriendAddReaction
import com.teampome.pome.data.remote.request.RequestFriendsData
import com.teampome.pome.data.remote.response.*
import retrofit2.http.*

interface FriendService {
    @GET("friends")
    suspend fun getAllFriends(): BaseResponse<List<ResponseFriendsProflie>>

    @POST("friends")
    suspend fun setAddFriends(
        @Body body: RequestFriendsData
    ): BaseResponse<Unit>

    @GET("friends/search")
    suspend fun getAddFriends(@Query("nickname") nickname: String): BaseResponse<List<ResponseFriendsData>>

    @GET("friends/records")
    suspend fun getFriendsRecords(@Query("userId") userId: Int): BaseResponse<List<ResponseFriendsAll>>

    @POST("records/reaction")
    suspend fun setFriendsReaction(
        @Body body: RequestFriendAddReaction
    ): BaseResponse<Unit>

    @GET("records/{recordId}/reaction")
    suspend fun getFriendsReaction(
        @Path("recordId") recordId: Int,
        @Query("type") type: Int
    ): BaseResponse<List<ResponseFriendReaction>>
}