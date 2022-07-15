package com.teampome.pome.data.repository

import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseUser
import com.teampome.pome.data.remote.response.UserService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: UserService
): AuthRepository {
    override suspend fun getUser(): BaseResponse<ResponseUser> {
        return service.getUser()
    }
}