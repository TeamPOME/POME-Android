package com.teampome.pome.presentation.record

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityRecordLookBackBinding
import com.teampome.pome.util.CustomItemDecorator
import com.teampome.pome.util.VerticalItemDecorator

class RecordLookBackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordLookBackBinding
    private lateinit var recordAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordLookBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBackClick()
    }

    private fun initAdapter(){
        recordAdapter = RecordAdapter()
        binding.rvRecord.adapter = recordAdapter
        addList()
        initDecoration()
    }

    private fun initDecoration() {
        binding.rvRecord.addItemDecoration(CustomItemDecorator(12))
        binding.rvRecord.addItemDecoration(VerticalItemDecorator(6))
    }

    private fun addList() {
        recordAdapter.submitList(
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

    private fun initBackClick() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}