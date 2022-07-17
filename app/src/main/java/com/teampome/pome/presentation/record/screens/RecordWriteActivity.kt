package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityRecordWriteBinding
import com.teampome.pome.presentation.record.emotion.BeforeSelectEmotionActivity
import com.teampome.pome.presentation.record.viewmodels.RecordWriteViewModel

class RecordWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordWriteBinding
    private val viewModel by viewModels<RecordWriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordWriteViewModel = viewModel
        binding.lifecycleOwner = this

        goBack()
        calendarClickEvent()
        checkComplete()
        goBeforeSelectEmotionActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun calendarClickEvent() {
        binding.btnCalendar.setOnClickListener {
            val bottomSheet = RecordCalendarBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }

    private fun checkComplete() {
//        viewModel.consumedate.observe(this) {
//            viewModel.completeWriteCheck()
//        }
        viewModel.consumeamount.observe(this) {
            viewModel.completeWriteCheck()
        }
        viewModel.consumerecord.observe(this) {
            viewModel.completeWriteCheck()
        }

        viewModel.isWriteSuccess.observe(this) {
            binding.btnWrite.isSelected = it
        }
    }

    private fun goBeforeSelectEmotionActivity() {
        binding.btnWrite.setOnClickListener {
            if (binding.btnWrite.isSelected) {
                val intent = Intent(this, BeforeSelectEmotionActivity::class.java)
                startActivity(intent)
            }
        }
    }
}