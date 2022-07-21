package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.teampome.pome.data.local.PomeDataStore
import com.teampome.pome.data.service.AuthService
import com.teampome.pome.databinding.ActivityKakaoLoginBinding
import com.teampome.pome.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class KakaoLoginActivity : AppCompatActivity() {
    @Inject
    lateinit var authService: AuthService
    private lateinit var binding: ActivityKakaoLoginBinding
    //private val viewModel by viewModels<KaKaoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initKakaoLogin()
        goKaKao()
    }


    private fun goKaKao() {
        binding.btnKakao.setOnClickListener {
            initKakaoLogin()
        }
    }

    private fun initKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                //Login Fail
            } else if (token != null) {
                //Login Success
                Log.d("token", "${token.accessToken}")
                lifecycleScope.launch {
                    runCatching {
                        authService.login("kakao", token.accessToken)
                    }.onSuccess {
                        //pome 회원이 아닐경우
                        if (it.data?.type =="signup")
                            PomeDataStore(this@KakaoLoginActivity).userToken = token.accessToken
                        goMainActivity()
                        //pome 회원일 경우
                    }.onFailure {
                        Timber.e("failure", "$it")
                    }
                }
            }
        }
        loginClickEvent(callback)
    }

    private fun loginClickEvent(callback: (OAuthToken?, Throwable?) -> Unit) {
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            UserApiClient.instance.run {
                if (isKakaoTalkLoginAvailable(this@KakaoLoginActivity)) {
                    loginWithKakaoTalk(this@KakaoLoginActivity, callback = callback)
                } else {
                    loginWithKakaoAccount(this@KakaoLoginActivity, callback = callback)
                }
            }
    }

    private fun goActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}


//내꺼 코드 아래임
/*private fun initKakaoLogin() {
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            //Login Fail
        } else if (token != null) {
            //Login Success
            Log.d("token", "$token")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    loginClickEvent(callback)
}

private fun goSignUpAcitivty() {
        // startActivity(Intent(this, SignUpActivity::class.java))
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        if (!isFinishing) {
            finish()
        }

    }

private fun loginClickEvent(callback: (OAuthToken?, Throwable?) -> Unit) {
    binding.btnKakao.setOnClickListener {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        *//*lifecycleScope.launch {
            authService.login(type, id, )
        }*//*
        UserApiClient.instance.run {
            if (isKakaoTalkLoginAvailable(this@KakaoLoginActivity)) {
                loginWithKakaoTalk(this@KakaoLoginActivity, callback = callback)
            } else {
                loginWithKakaoAccount(this@KakaoLoginActivity, callback = callback)
            }
        }
    }*/


/*
*
    private fun getAccessToken() {
        Log.e("click2","click2")
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            Log.e("click3","click3")
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("login","들어옴")
                    Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                } else if (token != null) {
                    showToast("로그인 성공")
                    Timber.tag(ContentValues.TAG).i("로그인 성공 [${token.accessToken}]")
                    lifecycleScope.launch {
                        runCatching {
                            authService.login("kakao", token.accessToken, token.refreshToken)
                        }.onSuccess {
                            val data = it.data
                            if (data?.type == "signup") //회원이 아닐경우
                                PomeDataStore(this@KakaoLoginActivity).userToken =
                                    it.data.accessToken
                            goSignUpAcitivty()
                        }.onFailure {
                            Timber.d("$it")
                        }
                    }
                } /*else {
                    UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                        if (error != null) {
                            Timber.tag(ContentValues.TAG).e(error, "로그인 실패")
                        } else if (token != null) {
                            Timber.tag(ContentValues.TAG).i("로그인 성공 ${token.accessToken}")
                            lifecycleScope.launch {
                                runCatching {
                                    authService.login(
                                        "kakao", token.accessToken, token.refreshToken
                                    )
                                }.onSuccess {
                                    val data = it.data
                                    if (data?.type == "signup") //회원이 아닐경우
                                        PomeDataStore(this@KakaoLoginActivity).userToken =
                                            it.data!!.accessToken
                                    goSignUpAcitivty()
                                }.onFailure {
                                    Timber.d("$it")
                                }
                            }
                        }
                    }
                }
            }*/

            }
        }*/