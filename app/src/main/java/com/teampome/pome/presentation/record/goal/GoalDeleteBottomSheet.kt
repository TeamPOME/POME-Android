package com.teampome.pome.presentation.record.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentGoalDeleteBottomSheetBinding
import com.teampome.pome.util.setOnSingleClickListener

class GoalDeleteBottomSheet(private val goalId: Int) : BottomSheetDialogFragment() {

    private var _binding: FragmentGoalDeleteBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalDeleteBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
        deleteEvent()
    }

    private fun initSetHeight() {
        val height = resources.displayMetrics.heightPixels * 0.2
        val bottomSheet = binding.clBottomsheet
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    private fun deleteEvent() {
        binding.liRemove.setOnSingleClickListener {
            val dialog = DeleteGoalDialogFragment(goalId)
            activity?.let { it1 ->
                dialog.show(
                    it1.supportFragmentManager,
                    "DeleteGoalDialogFragment"
                )
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}