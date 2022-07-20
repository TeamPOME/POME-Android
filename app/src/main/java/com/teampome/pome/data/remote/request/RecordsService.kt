package com.teampome.pome.data.remote.request

import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseRecordsCreate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecordsService {
    @POST("records")
    fun createRecord(
        @Body body: RequestRecordsCreate
    ): Call<BaseResponse<ResponseRecordsCreate>>
}