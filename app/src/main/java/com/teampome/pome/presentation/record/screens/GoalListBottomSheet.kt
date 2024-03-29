package com.teampome.pome.presentation.record.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.data.GoalService
import com.teampome.pome.databinding.FragmentGoalListBottomSheetBinding
import com.teampome.pome.presentation.record.adapters.GoalListAdapter
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class GoalListBottomSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var service: GoalService
    private var _binding: FragmentGoalListBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var goalListAdapter: GoalListAdapter

    private var onListenerGoal: OnListenerGoal? = null

    interface OnListenerGoal {
        fun onCheckedGoal(goal: String, id: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onListenerGoal = activity as OnListenerGoal
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalListBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGoalList()
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
        binding.btnCancel.setOnSingleClickListener {
            dismiss()
        }
    }

    private fun initAdapter() {
        goalListAdapter = GoalListAdapter {
            onListenerGoal?.onCheckedGoal(it.category, it.id)
            dismiss()
        }
        binding.rvGoal.adapter = goalListAdapter
    }

    private fun initGoalList() {
        lifecycleScope.launch {
            runCatching {
                service.initGoalChip()
            }.onSuccess {
                goalListAdapter.submitList(it.data)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }


    override fun onDetach() {
        super.onDetach()
        onListenerGoal = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}