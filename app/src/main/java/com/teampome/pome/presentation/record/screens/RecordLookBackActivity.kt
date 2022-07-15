package com.teampome.pome.presentation.record.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityRecordLookBackBinding
import com.teampome.pome.presentation.record.RecordAdapter
import com.teampome.pome.presentation.record.RecordData
import com.teampome.pome.presentation.record.RecordLookBackAdapter
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator

class RecordLookBackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordLookBackBinding
    private lateinit var recordLookBackAdapter: RecordLookBackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordLookBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        goBack()
    }

    private fun initAdapter(){
        recordLookBackAdapter = RecordLookBackAdapter()
        binding.rvRecord.adapter = recordLookBackAdapter
        addList()
        initDecoration()
    }

    private fun initDecoration() {
        binding.rvRecord.addItemDecoration(CustomItemDecorator(12))
        binding.rvRecord.addItemDecoration(VerticalItemDecorator(6))
    }

    private fun addList() {
        recordLookBackAdapter.submitList(
            listOf(
                RecordData(1, "06.24", 1, "10,000원", "뽀뽀랑 오랜만에 술 한잔! 헤헤 맛있당 ^_^"),
                RecordData(1, "06.25", 1, "100,000원", "가보자고... 10만원의 무언가ㅎㅎ"),
                RecordData(1, "06.26", 1, "200,000원", "확인용"),
                RecordData(1, "06.27", 1, "300,000원", "술 조지고싶다"),
                RecordData(1, "06.28", 1, "500,000원", "배고파"),
                RecordData(1, "06.29", 1, "1,000,000원", "누가 백만원을 한번에 긁어")
            )
        )
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}