package com.teampome.pome.data.repository

import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseUser

interface AuthRepository {
    suspend fun getUser(): BaseResponse<ResponseUser>
}