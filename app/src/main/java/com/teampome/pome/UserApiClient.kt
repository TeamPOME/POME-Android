package com.teampome.pome

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 카카오톡으로 로그인 시도
 */
suspend fun UserApiClient.Companion.loginWithKakaoTalk(context: Context): OAuthToken {
    return suspendCoroutine<OAuthToken> { continuation ->
        instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Timber.e(error, "로그인 실패")
            } else if (token != null) {
                Timber.i("로그인 성공 $token.accessToken")
            } else {
               // else 문 분기 처리하기
            }
        }
    }
}

/*
else {
    UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
        if (error != null) {
            Timber.e(error, "로그인 실패")
        } else if (token != null) {
            Timber.i("로그인 성공 ${token.accessToken}")
        }
    }*/
//  continuation.resumeWithException(RuntimeException("kakao access token을 받아오는데 실패함, 이유는 명확하지 않음."))
//            }