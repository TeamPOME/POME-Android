package com.teampome.pome.data

import com.teampome.pome.data.remote.request.RequestRecordsCreate
import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseRecordsCreate
import com.teampome.pome.data.remote.response.ResponseRecordsLook
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RecordsService {
    @POST("records")
    fun createRecord(
        @Body body: RequestRecordsCreate
    ): Call<BaseResponse<ResponseRecordsCreate>>

    @GET("records/{goalId}")
    fun initGoalRecord(
        @Path("goalId") goalId: Int
    ): Call<BaseResponse<List<ResponseRecordsLook>>>
}