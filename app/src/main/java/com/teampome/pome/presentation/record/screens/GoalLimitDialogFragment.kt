package com.teampome.pome.presentation.record.screens

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentGoalLimitDialogBinding

class GoalLimitDialogFragment : DialogFragment() {

    private var _binding: FragmentGoalLimitDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalLimitDialogBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_goal_limit_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTextColor()
        cancelDialog()
    }

    private fun cancelDialog() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun changeTextColor() {
        val color = SpannableStringBuilder("목표는 5개까지만 만들 수 있어요")
        color.apply {
            setSpan(ForegroundColorSpan(Color.parseColor(R.color.pome_sub.toString())),4,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.tvLimitgoal.text = color
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}