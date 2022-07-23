package com.teampome.pome.presentation.record.emotion

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityAfterSelectEmotionBinding
import com.teampome.pome.util.setOnSingleClickListener

class AfterSelectEmotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterSelectEmotionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_after_select_emotion)
        setContentView(binding.root)
        goBack()
        clickIcon()
        goToCompleteActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun clickIcon() {
        binding.btnHappy.setOnClickListener {
            binding.apply {
                btnOh.isSelected = false
                btnSad.isSelected = false
                btnHappy.isSelected = true
                btnComplete.isSelected = true
                tvHappy.isSelected = true
                tvOh.isSelected = false
                tvSad.isSelected = false
            }

        }
        binding.btnOh.setOnClickListener {
            binding.apply {
                btnHappy.isSelected = false
                btnSad.isSelected = false
                btnOh.isSelected = true
                btnComplete.isSelected = true
                tvHappy.isSelected = false
                tvOh.isSelected = true
                tvSad.isSelected = false
            }

        }
        binding.btnSad.setOnClickListener {
            binding.apply {
                btnOh.isSelected = false
                btnHappy.isSelected = false
                btnSad.isSelected = true
                btnComplete.isSelected = true
                tvHappy.isSelected = false
                tvOh.isSelected = false
                tvSad.isSelected = true
            }

        }
    }

    private fun goToCompleteActivity() {
        binding.btnComplete.setOnClickListener {
            if (binding.btnComplete.isSelected) {
                Intent(this, AfterCompleteEmotionActivity::class.java).run {
                    startActivity(this)
                    finish()
                }
            }
        }
    }
}