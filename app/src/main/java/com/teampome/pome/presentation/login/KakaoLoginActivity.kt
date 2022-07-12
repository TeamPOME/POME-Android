package com.teampome.pome.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kakao.sdk.common.util.Utility
import com.teampome.pome.R
import timber.log.Timber

class KakaoLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_login)

        val keyHash = Utility.getKeyHash(this)
        Timber.e("Key", "keyHash: ${keyHash}")
    }
    }
