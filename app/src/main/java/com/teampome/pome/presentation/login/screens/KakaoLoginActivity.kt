package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.teampome.pome.databinding.ActivityKakaoLoginBinding
import timber.log.Timber


class KakaoLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initKakaoLogin()
    }


    private fun initKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                //Login Fail
            } else if (token != null) {
                //Login Success
                    Log.d("token","$token")
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
        loginClickEvent(callback)
    }

    private fun loginClickEvent(callback: (OAuthToken?, Throwable?) -> Unit) {
        binding.btnKakao.setOnClickListener {
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            UserApiClient.instance.run {
                if (isKakaoTalkLoginAvailable(this@KakaoLoginActivity)) {
                    loginWithKakaoTalk(this@KakaoLoginActivity, callback = callback)
                } else {
                    loginWithKakaoAccount(this@KakaoLoginActivity, callback = callback)
                }
            }
        }
    }

}

