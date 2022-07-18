package com.teampome.pome.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import com.teampome.pome.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PomeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //KakaoSdk.init(this, "{NATIVE_APP_KEY}")
        //KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}