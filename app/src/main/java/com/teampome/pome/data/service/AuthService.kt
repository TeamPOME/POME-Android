package com.teampome.pome.data.service

import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseAuth
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AuthService {
    @GET("auth/{social}")
      suspend fun login(
        @Path("social") social: String,
        @Header("token") accessToken: String
    ): BaseResponse<ResponseAuth>
}
