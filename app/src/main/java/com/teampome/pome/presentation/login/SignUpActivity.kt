package com.teampome.pome.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import com.teampome.pome.databinding.ActivitySignUpBinding
import timber.log.Timber

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnClickEvent()
    }

    private fun btnClickEvent() {
        binding.ivPlus.setOnClickListener {
            goGallery()
        }
    }

    private val activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                var currentImageUri = result.data?.data
                binding.ivProfile.load(currentImageUri)
            } else if (result.resultCode == RESULT_CANCELED) {
                Timber.d("사진선택실패")
            } else {
                Timber.d("사진첨부실패")
            }
        }

    private fun goGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activityLauncher.launch(intent)
    }

}