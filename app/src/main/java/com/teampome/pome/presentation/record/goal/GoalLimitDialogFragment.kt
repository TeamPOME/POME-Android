package com.teampome.pome.presentation.record.goal

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.teampome.pome.databinding.FragmentGoalLimitDialogBinding
import com.teampome.pome.util.setOnSingleClickListener

class GoalLimitDialogFragment : DialogFragment() {

    private var _binding: FragmentGoalLimitDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalLimitDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTextColor()
        cancelDialog()
        backgroundDesign()
    }

    private fun cancelDialog() {
        binding.btnCancel.setOnSingleClickListener {
            dismiss()
        }
    }

    private fun changeTextColor() {
        val color = SpannableStringBuilder("목표는 10개까지만 만들 수 있어요")
        color.apply {
            setSpan(ForegroundColorSpan(Color.parseColor("#ff908a")),4,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.tvLimitgoal.text = color
    }

    private fun backgroundDesign() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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