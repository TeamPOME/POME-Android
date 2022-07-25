package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.teampome.pome.R
import com.teampome.pome.data.GoalService
import com.teampome.pome.data.RecordsService
import com.teampome.pome.databinding.ActivityRecordLookBackBinding
import com.teampome.pome.presentation.record.RecordData
import com.teampome.pome.presentation.record.adapters.RecordLookBackAdapter
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RecordLookBackActivity : AppCompatActivity() {

    @Inject
    lateinit var service: RecordsService

    @Inject
    lateinit var goalservice: GoalService

    private lateinit var binding: ActivityRecordLookBackBinding
    private lateinit var recordLookBackAdapter: RecordLookBackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordLookBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initGoalName()
        goBack()
    }

    private fun initAdapter() {
        recordLookBackAdapter = RecordLookBackAdapter()
        binding.rvRecord.adapter = recordLookBackAdapter
        initDecoration()
    }

    private fun initGoalName() {
        val intent = intent
        val goalId = intent.getIntExtra("goalId", 0)
        lifecycleScope.launch {
            runCatching {
                goalservice.initGoalDetail(goalId)
            }.onSuccess {
                if (it.data?.isPublic == true) {
                    binding.ivLock.setImageResource(R.drawable.ic_unlock)
                } else {
                    binding.ivLock.setImageResource(R.drawable.ic_lock_all)
                }
                binding.tvTitle.text = it.data?.message
                initRecordList(goalId)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initRecordList(goalId: Int) {
        lifecycleScope.launch {
            runCatching {
                service.initRecordList(goalId)
            }.onSuccess {
                recordLookBackAdapter.submitList(it.data?.records)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initDecoration() {
        binding.rvRecord.addItemDecoration(CustomItemDecorator(12))
        binding.rvRecord.addItemDecoration(VerticalItemDecorator(6))
    }

    private fun goBack() {
        binding.btnBack.setOnSingleClickListener {
            finish()
        }
    }
}