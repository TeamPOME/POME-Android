package com.teampome.pome.data

import com.teampome.pome.data.remote.request.RequestGoalCreate
import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseGoalCreate
import com.teampome.pome.data.remote.response.ResponseGoalDetail
import retrofit2.Call
import retrofit2.http.*

interface GoalService {
    @POST("goals")
    suspend fun createGoal(
        @Body body: RequestGoalCreate
    ): BaseResponse<ResponseGoalCreate>

    @GET("goals")
    suspend fun initGoalChip(
    ): BaseResponse<List<ResponseGoalCreate>>

    @GET("goals/{goalId}")
    suspend fun initGoalDetail(
        @Path("goalId") goalId: Int
    ): BaseResponse<ResponseGoalDetail>

    @DELETE("goals/{goalId}")
    suspend fun deleteGoal(
        @Path("goalId") goalId: Int
    ): BaseResponse<Unit>
}