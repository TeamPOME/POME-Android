package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityKakaoLoginBinding
import com.teampome.pome.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KakaoLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoLoginBinding
    //private val viewModel by viewModels<KaKaoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //observeAccessToken()
        goKaKao()
    }


    private fun goKaKao() {
        binding.btnKakao.setOnClickListener {
            goMainActivity()
        }
    }

    /* private fun getAccessToken() {
         if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
             UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                 if (error != null) {
                     Log.e("failure", "실패")
                 } else if (token != null) {
                     Log.e("success", "성공1")
                     viewModel.login("kakao", token.accessToken)
                 }
             }
         } else {
             UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                 if (error != null) {
                     Log.e("failure", "실패")
                 } else if (token != null) {
                     Log.e("success", "성공2")
                     viewModel.login("kakao", token.accessToken)
                 }
             }
         }
     }*/

    private fun goMainActivity() {
        //PomeDataStore(this).userToken = it
        startActivity(Intent(this, MainActivity::class.java))
        if (!isFinishing) {
            finish()
        }
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
        }
          private fun goActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }*/
