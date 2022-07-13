package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindFirstBottomSheetBinding
import com.teampome.pome.databinding.FragmentRemindSecondBottomSheetBinding

class RemindSecondBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindSecondBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindSecondBottomSheetBinding.inflate(layoutInflater, container, false)

        initHeight()
        initClose()

        return binding.root
    }

    private fun initHeight() {
        val bottomSheet = binding.clRemindSecondBottomsheet
        bottomSheet.minHeight = 259
        bottomSheet.maxHeight = 259
    }

    private fun initClose() {
        binding.ivClose.setOnClickListener {
            dialog?.dismiss()
        }
    }
}