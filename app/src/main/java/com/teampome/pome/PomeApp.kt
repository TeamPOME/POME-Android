package com.teampome.pome

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk


class PomeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //KakaoSdk.init(this, "{NATIVE_APP_KEY}")
        //-> KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}