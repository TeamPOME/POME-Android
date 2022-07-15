package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityRecordWriteBinding

class RecordWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        goRecordAddActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun goRecordAddActivity() {
        binding.btnWrite.setOnClickListener {
            val intent = Intent(this, RecordAddActivity::class.java)
            startActivity(intent)
        }
    }
}