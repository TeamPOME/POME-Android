package com.teampome.pome.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teampome.pome.data.remote.response.BaseResponse
import com.teampome.pome.data.remote.response.ResponseUser
import com.teampome.pome.util.FileParser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(
    private val parser: FileParser,
    @ApplicationContext private val context:Context
) : AuthRepository {
    override suspend fun getUser(): BaseResponse<ResponseUser> {
        return withContext(Dispatchers.IO) {
            val file = runCatching {
                parser.execute("fake_user.json")
            }
            Gson().fromJson(file.getOrNull(), com.teampome.pome.data.repository.typeOf<BaseResponse<ResponseUser>>())
        }
    }
}

inline fun <reified T> typeOf(): Type = object : TypeToken<T>() {}.type