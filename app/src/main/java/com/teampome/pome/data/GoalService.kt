package com.teampome.pome.data

import com.teampome.pome.data.remote.request.RequestGoalCreate
import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseGoalCreate
import com.teampome.pome.data.remote.response.ResponseGoalDetail
import retrofit2.Call
import retrofit2.http.*

interface GoalService {
    @POST("goals")
    fun createGoal(
        @Body body: RequestGoalCreate
    ): Call<BaseResponse<ResponseGoalCreate>>

    @GET("goals")
    fun initGoalChip(
    ): Call<BaseResponse<List<ResponseGoalCreate>>>

    @GET("goals/{goalId}")
    fun initGoalDetail(
        @Path("goalId") goalId: Int
    ): Call<BaseResponse<ResponseGoalDetail>>

    @DELETE("goals/{goalId}")
    fun deleteGoal(
        @Path("goalId") goalId: Int
    ): Call<BaseResponse<Unit>>
}