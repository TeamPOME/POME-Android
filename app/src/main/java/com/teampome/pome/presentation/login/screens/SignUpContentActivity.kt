package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivitySignUpContentBinding

class SignUpContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToAddFriendActivity()
    }

    private fun goToAddFriendActivity() {
        binding.btnFinish.setOnClickListener {
            Intent(this@SignUpContentActivity, AddFriendActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }
    }
}