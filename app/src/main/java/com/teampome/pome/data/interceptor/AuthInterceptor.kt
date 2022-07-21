package com.teampome.pome.data.interceptor
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    //private val pomeDataStore: PomeDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder()
            //.addHeader("token", pomeDataStore.userToken).build()
            .addHeader(
                "Authorization",
                "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzIsInNvY2lhbCI6Imtha2FvIiwidXVpZCI6IjIzNDU1NDU3NTEiLCJuaWNrbmFtZSI6IjEyc3ViZWVuIiwicHJvZmlsZUltYWdlIjoiZGVmYXVsdF9pbWFnZS5wbmciLCJyZWZyZXNoVG9rZW4iOiJleUpoYkdjaU9pSklVekkxTmlJc0luUjVjQ0k2SWtwWFZDSjkuZXlKcFlYUWlPakUyTlRneE5qQTRNVFFzSW1WNGNDSTZNVFkyTlRrek5qZ3hOSDAuNzFFMDNtQ0dSYWxYSVV2QXFDdXY2dng0b1k1YnZHUndiQ0lhVndYYmUwZyIsImRldmljZVRva2VuIjpudWxsLCJjcmVhdGVkQXQiOiIyMDIyLTA3LTE5VDAwOjM4OjM3LjMxMFoiLCJ1cGRhdGVkQXQiOiIyMDIyLTA3LTE5VDAwOjM4OjM3LjMxMFoiLCJpc0RlbGV0ZWQiOmZhbHNlLCJpYXQiOjE2NTgxNjA4MTQsImV4cCI6MTY2NTkzNjgxNH0.hIwyKFnzjLd5nYqvUlQN9p5U9AsBxeGchVpzA5LAEwA"
            ).build()
        return chain.proceed(authRequest)
    }
}

/*val authRequest = chain.request().newBuilder()
    .addHeader("token", localStorage.userToken).build()
//.addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzIsInNvY2lhbCI6Imtha2FvIiwidXVpZCI6IjIzNDU1NDU3NTEiLCJuaWNrbmFtZSI6IjEyc3ViZWVuIiwicHJvZmlsZUltYWdlIjoiZGVmYXVsdF9pbWFnZS5wbmciLCJyZWZyZXNoVG9rZW4iOiJleUpoYkdjaU9pSklVekkxTmlJc0luUjVjQ0k2SWtwWFZDSjkuZXlKcFlYUWlPakUyTlRneE5qQTRNVFFzSW1WNGNDSTZNVFkyTlRrek5qZ3hOSDAuNzFFMDNtQ0dSYWxYSVV2QXFDdXY2dng0b1k1YnZHUndiQ0lhVndYYmUwZyIsImRldmljZVRva2VuIjpudWxsLCJjcmVhdGVkQXQiOiIyMDIyLTA3LTE5VDAwOjM4OjM3LjMxMFoiLCJ1cGRhdGVkQXQiOiIyMDIyLTA3LTE5VDAwOjM4OjM3LjMxMFoiLCJpc0RlbGV0ZWQiOmZhbHNlLCJpYXQiOjE2NTgxNjA4MTQsImV4cCI6MTY2NTkzNjgxNH0.hIwyKFnzjLd5nYqvUlQN9p5U9AsBxeGchVpzA5LAEwA").build()
return chain.proceed(authRequest)*/


/*
  when (response.code) {
            401 -> {
                val refreshTokenRequest = originalRequest.newBuilder().get()
                    .url("${BASE_URL}kakao/token")
                    .addHeader("token", localStorage.userToken)
                    .build()
                val refreshTokenResponse = chain.proceed(refreshTokenRequest)

                if (refreshTokenResponse.isSuccessful) {
                    with(localStorage) {
                        userToken = refreshToken
                        this.refreshToken = refreshToken
                    }
                    val newRequest = originalRequest.newBuilder()
                        .addHeader("token", localStorage.userToken)
                        .build()
                    return chain.proceed(newRequest)
                }
            }
        }*/