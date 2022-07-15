package com.teampome.pome.presentation.record.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.presentation.record.RecordAdapter
import com.teampome.pome.presentation.record.RecordData
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator

class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private lateinit var recordAdapter: RecordAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goGoalDateActivity()
        initAdapter()
//        noGoalClickEvent()
        fabClickEvent()
        goLookBackActivity()
    }

    private fun goGoalDateActivity() {
        binding.btnGoaladd.setOnClickListener {
            //나중에 로직 짤 때 목표가 5개인지 검사
            val intent = Intent(requireContext(), GoalDateActivity::class.java)
            startActivity(intent)
        }
        binding.btnMakegoal.setOnClickListener {
            //나중에 로직 짤 때 목표가 5개인지 검사
            val intent = Intent(requireContext(), GoalDateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAdapter() {
        recordAdapter = RecordAdapter()
        binding.rvRecord.adapter = recordAdapter
        addList()
        initDecoration()
    }

    private fun initDecoration() {
        binding.rvRecord.addItemDecoration(CustomItemDecorator(12))
        binding.rvRecord.addItemDecoration(VerticalItemDecorator(6))
    }

    private fun goLookBackActivity() {
        binding.clLookback.setOnClickListener {
            val intent = Intent(requireContext(), RecordLookBackActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addList() {
        recordAdapter.submitList(
            listOf(
                RecordData(1, "06.24", 1, "10,000원", "gg"),
                RecordData(1, "06.25", 1, "100,000원", "gg"),
                RecordData(1, "06.26", 1, "200,000원", "gg"),
                RecordData(1, "06.27", 1, "300,000원", "gg"),
                RecordData(1, "06.28", 1, "500,000원", "gg"),
                RecordData(1, "06.29", 1, "1,000,000원", "gg")
            )
        )
    }

//    private fun noGoalClickEvent() {
//        binding.fabWrite.setOnClickListener {
//            val dialog = NoRecordDialogFragment()
//            activity?.let { it1 -> dialog.show(it1.supportFragmentManager, "NoRecordDialogFragment") }
//        }
//    }

    private fun fabClickEvent() {
        binding.fabWrite.setOnClickListener {
            val intent = Intent(requireContext(), RecordWriteActivity::class.java)
            startActivity(intent)
        }
    }
}