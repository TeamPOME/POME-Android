package com.teampome.pome.data.remote.response

import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUser(): BaseResponse<ResponseUser>
}