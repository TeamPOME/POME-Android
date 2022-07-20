package com.teampome.pome.data.interceptor

import android.util.Log
import com.teampome.pome.data.local.PomeDataStore
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localStorage: PomeDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder()
            .addHeader("token", localStorage.userToken).build()
        Log.d("auth","$authRequest")
        return chain.proceed(authRequest)
    }
}