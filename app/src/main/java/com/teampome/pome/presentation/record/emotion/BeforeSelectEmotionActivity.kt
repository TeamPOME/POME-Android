package com.teampome.pome.presentation.record.emotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.teampome.pome.data.RecordsService
import com.teampome.pome.data.remote.request.RequestRecordsCreate
import com.teampome.pome.databinding.ActivityBeforeSelectEmotionBinding
import com.teampome.pome.presentation.record.screens.RecordAddActivity
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BeforeSelectEmotionActivity : AppCompatActivity() {

    @Inject
    lateinit var service: RecordsService
    private lateinit var binding: ActivityBeforeSelectEmotionBinding
    private var clickedPosition = 0

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
                clickedPosition = 1
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
                clickedPosition = 2
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
                clickedPosition = 3
            }
        }
        return clickedPosition
    }

    private fun goRecordAddActivity() {
        binding.btnComplete.setOnClickListener {
            if (binding.btnComplete.isSelected) {
                recordCreateNetwork()
            }
        }
    }

    private fun recordCreateNetwork() {
        val intent = intent
        val goalId = intent.getIntExtra("goalId", 0)
        val consumeDate = intent.getStringExtra("consumeDate")?.replace(".", "-")
        val consumeAmount = intent.getStringExtra("consumeAmount")?.toInt()
        val consumeRecord = intent.getStringExtra("consumeRecord")
        val emotion = clickIcon()

        val requestRecordsCreate = RequestRecordsCreate(
            goalId = goalId,
            date = consumeDate.toString(),
            amount = consumeAmount.toString().toInt(),
            content = consumeRecord.toString(),
            startEmotion = emotion
        )

        lifecycleScope.launch {
            runCatching {
                service.createRecord(requestRecordsCreate)
            }.onSuccess {
                startActivity(Intent(this@BeforeSelectEmotionActivity, RecordAddActivity::class.java))
                if (!isFinishing) finish()
            }.onFailure {
                Timber.d("$it")
            }
        }
    }
}