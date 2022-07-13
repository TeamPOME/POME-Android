package com.teampome.pome.presentation.remind

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindBottomSheetBinding

class RemindBottomSheetFragment :BottomSheetDialogFragment() {
    private var _binding:FragmentRemindBottomSheetBinding?=null
    private val binding get() = _binding?:error("binding이 되지 않았습니다")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentRemindBottomSheetBinding.inflate(layoutInflater,container,false)

        initHeight()

        return binding.root
    }
    private fun initHeight(){
        val bottomSheet=binding.clRemindBottomsheet
        bottomSheet.minHeight=259
        bottomSheet.maxHeight=259
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}