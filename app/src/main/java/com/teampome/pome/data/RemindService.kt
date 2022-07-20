package com.teampome.pome.data

import retrofit2.http.GET

interface RemindService {
    @GET
    suspend fun getRemindData()
}