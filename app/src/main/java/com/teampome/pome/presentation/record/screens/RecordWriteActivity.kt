package com.teampome.pome.presentation.record.screens

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
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}