package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentRemindFirstBottomSheetBinding

class RemindFirstBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindFirstBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindFirstBottomSheetBinding.inflate(layoutInflater, container, false)

        initHeight()
        initClose()
        return binding.root
    }

    private fun initHeight() {
        val bottomSheet = binding.clRemindFirstBottomsheet
        bottomSheet.minHeight = 259
        bottomSheet.maxHeight = 259
    }
    private fun initClose(){
        binding.ivClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}