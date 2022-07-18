package com.teampome.pome

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber
import kotlin.coroutines.suspendCoroutine

/**
 * 카카오톡으로 로그인 시도
 */
/*suspend fun UserApiClient.Companion.loginWithKakaoTalk(context: CoroutineScope): OAuthToken {
    return suspendCoroutine<OAuthToken> { continuation ->
        instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                Timber.e(error, "로그인 실패")
            } else if (token != null) {
                Timber.i("로그인 성공 $token.accessToken")
            } else {
                instance.loginWithKakaoAccount(this) { token, error ->
                    if (error != null) {
                        Timber.e(error, "로그인 실패")
                    } else if (token != null) {
                        Timber.i("로그인 성공 ${token.accessToken}")
                    }
                }
            }
        }
    }
}*/
