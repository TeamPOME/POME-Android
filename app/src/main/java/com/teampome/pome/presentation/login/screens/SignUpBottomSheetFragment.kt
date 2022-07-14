package com.teampome.pome.presentation.login.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentSignUpBottomSheetBinding

class SignUpBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSignUpBottomSheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
    }

    private fun initSetHeight() {
        //600으로
        val bottomSheet = binding.clBottomsheet
        bottomSheet.minHeight = 600
        bottomSheet.maxHeight = 600
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}