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
            .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzIsInNvY2lhbCI6Imtha2FvIiwidXVpZCI6IjIzNDU1NDU3NTEiLCJuaWNrbmFtZSI6IjEyc3ViZWVuIiwicHJvZmlsZUltYWdlIjoiZGVmYXVsdF9pbWFnZS5wbmciLCJyZWZyZXNoVG9rZW4iOiJleUpoYkdjaU9pSklVekkxTmlJc0luUjVjQ0k2SWtwWFZDSjkuZXlKcFlYUWlPakUyTlRneE5qQTRNVFFzSW1WNGNDSTZNVFkyTlRrek5qZ3hOSDAuNzFFMDNtQ0dSYWxYSVV2QXFDdXY2dng0b1k1YnZHUndiQ0lhVndYYmUwZyIsImRldmljZVRva2VuIjpudWxsLCJjcmVhdGVkQXQiOiIyMDIyLTA3LTE5VDAwOjM4OjM3LjMxMFoiLCJ1cGRhdGVkQXQiOiIyMDIyLTA3LTE5VDAwOjM4OjM3LjMxMFoiLCJpc0RlbGV0ZWQiOmZhbHNlLCJpYXQiOjE2NTgxNjA4MTQsImV4cCI6MTY2NTkzNjgxNH0.hIwyKFnzjLd5nYqvUlQN9p5U9AsBxeGchVpzA5LAEwA").build()
        return chain.proceed(authRequest)
    }
}