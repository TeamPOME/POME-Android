package com.teampome.pome.presentation.login.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentSignUpBottomSheetBinding

class SignUpBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSignUpBottomSheetBinding? = null
    private val binding get() = _binding!!

    // bottom sheet 클릭 값 액티비티 전달
    private var onListenerButton: OnListenerButton? = null

    interface OnListenerButton {
        fun onCheckedState(state: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onListenerButton = activity as OnListenerButton
    }

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
        clickEvent()
    }

    private fun initSetHeight() {
        //600으로
        val bottomSheet = binding.clBottomsheet
        bottomSheet.minHeight = 600
        bottomSheet.maxHeight = 600
    }

    private fun clickEvent() {
        binding.liModify.setOnClickListener {
            onListenerButton?.onCheckedState(0)
        }
        binding.liRemove.setOnClickListener {
            onListenerButton?.onCheckedState(1)
        }
      //  dismiss()
    }

    override fun onDetach() {
        super.onDetach()
        onListenerButton = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}