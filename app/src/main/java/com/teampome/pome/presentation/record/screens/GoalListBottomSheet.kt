package com.teampome.pome.presentation.record.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentGoalListBottomSheetBinding
import com.teampome.pome.presentation.record.GoalListData
import com.teampome.pome.presentation.record.adapters.GoalListAdapter

class GoalListBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentGoalListBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var goalListAdapter: GoalListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalListBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
        initClose()
        initAdapter()
    }

    private fun initSetHeight() {
        val height = resources.displayMetrics.heightPixels * 0.5
        val bottomSheet = binding.clGoallist
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    private fun initClose() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initAdapter() {
        goalListAdapter = GoalListAdapter()
        binding.rvGoal.adapter = goalListAdapter
        addList()
    }

    private fun addList() {
        goalListAdapter.submitList(
            listOf(
                GoalListData("커피"),
                GoalListData("술"),
                GoalListData("밥"),
                GoalListData("스티커"),
                GoalListData("앨범"),
                GoalListData("옷"),
                GoalListData("가전제품"),
                GoalListData("적금"),
                GoalListData("구독"),
                GoalListData("포미")
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}