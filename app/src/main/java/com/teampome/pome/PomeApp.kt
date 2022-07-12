package com.teampome.pome

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

class PomeApp : Application() {
    override fun onCreate() {
        super.onCreate()
       // KakaoSdk.init(this, BuildConfig.APIKEY)
    }
}