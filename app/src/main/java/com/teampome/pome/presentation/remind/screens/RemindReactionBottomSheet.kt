package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindReactionBottomSheetBinding
import com.teampome.pome.presentation.friends.FriendReactionData
import com.teampome.pome.presentation.remind.RemindReactionData
import com.teampome.pome.presentation.remind.adapters.RemindReactionAdapter

class RemindReactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindReactionBottomSheetBinding?=null
    private val binding get() = _binding?:error("binding이 되지 않는다.")
    private lateinit var remindReactionAdapter:RemindReactionAdapter
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
        initAdapter()
    }

    private fun initAdapter(){
        remindReactionAdapter= RemindReactionAdapter()
        binding.rvRemindReaction.adapter=remindReactionAdapter
    }
    private fun initHeight(){
        val bottomSheet = binding.clRemindbottomsheet
        val height = resources.displayMetrics.heightPixels*0.6
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}