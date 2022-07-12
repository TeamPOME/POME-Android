package com.teampome.pome.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityKakaoLoginBinding


class KakaoLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}
