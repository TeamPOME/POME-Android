package com.teampome.pome.data

import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseRemindData
import com.teampome.pome.data.remote.response.ResponseRemindGoal
import retrofit2.http.GET
import retrofit2.http.Query

interface RemindService {
    @GET("records")
    suspend fun getRemindData(
        @Query("goalId") goalId: Int,
        @Query("startEmotion") startEmotion: Int,
        @Query("endEmotion") endEmotion: Int
    ): BaseResponse<List<ResponseRemindData>>

    @GET("goals")
    suspend fun getRemindGoal(): BaseResponse<List<ResponseRemindGoal>>
}