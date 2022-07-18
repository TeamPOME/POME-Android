package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindReactionBottomSheetBinding

class RemindReactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindReactionBottomSheetBinding?=null
    private val binding get() = _binding?:error("binding이 되지 않는다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding=FragmentRemindReactionBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeight()
    }
    private fun initHeight(){
        val bottomSheet = binding.clRemindReactionBottomsheet
        bottomSheet.minHeight = ViewGroup.LayoutParams.WRAP_CONTENT
        bottomSheet.maxHeight = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}