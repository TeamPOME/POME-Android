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
            .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjYsInNvY2lhbCI6Imtha2FvIiwidXVpZCI6IjExMjIzMyIsIm5pY2tuYW1lIjoiMTEyMjMzIiwicHJvZmlsZUltYWdlIjoiZGVmYXVsdF9pbWFnZS5wbmciLCJyZWZyZXNoVG9rZW4iOiJleUpoYkdjaU9pSklVekkxTmlJc0luUjVjQ0k2SWtwWFZDSjkuZXlKcFlYUWlPakUyTlRjMk56VTVORGdzSW1WNGNDSTZNVFkyTURJMk56azBPSDAuRVhpeG91dXFTNFJTZFgzeGt1TGozWXBlQWRLODlhdktURl9zTU5JbW1KVSIsImRldmljZVRva2VuIjpudWxsLCJjcmVhdGVkQXQiOiIyMDIyLTA3LTEzVDEwOjMyOjI4Ljc4MFoiLCJ1cGRhdGVkQXQiOiIyMDIyLTA3LTEzVDEwOjMyOjI4Ljc4MFoiLCJpc0RlbGV0ZWQiOmZhbHNlLCJpYXQiOjE2NTc2NzU5NDgsImV4cCI6MTY2MDI2Nzk0OH0.Sy29Lz0-LkNbogvH16RWCxetl_9JASW0uKwfegbhiUg").build()
        return chain.proceed(authRequest)
    }
}