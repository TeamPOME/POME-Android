package com.teampome.pome.presentation.record.emotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityBeforeSelectEmotionBinding
import com.teampome.pome.presentation.record.screens.RecordAddActivity

class BeforeSelectEmotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeforeSelectEmotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeforeSelectEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goBack()
        clickIcon()
        goRecordAddActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun clickIcon(): Int {
        var emotion = 0
        binding.btnHappy.setOnClickListener {
            binding.apply {
                btnOh.isSelected = false
                btnSad.isSelected = false
                btnHappy.isSelected = true
                btnComplete.isSelected = true
                tvHappy.isSelected = true
                tvOh.isSelected = false
                tvSad.isSelected = false
                btnComplete.isSelected = true
                emotion = 1
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
                btnComplete.isSelected = true
                emotion = 2
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
                btnComplete.isSelected = true
                emotion = 3
            }
        }
        return emotion
    }

    private fun goRecordAddActivity() {
        binding.btnComplete.setOnClickListener {
            if (binding.btnComplete.isSelected) {
                Intent(this, RecordAddActivity::class.java).run {
                    startActivity(this)
                    finish()
                }
            }
        }
    }
}