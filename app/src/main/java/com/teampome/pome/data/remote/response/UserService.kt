package com.teampome.pome.data.remote.response

import retrofit2.http.GET

interface UserService {
    @GET
    suspend fun getUser(): BaseResponse<ResponseUser>
}