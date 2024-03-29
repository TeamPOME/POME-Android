package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentRemindFirstBottomSheetBinding
import com.teampome.pome.util.setOnSingleClickListener

class RemindFirstBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindFirstBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindFirstBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
        initClose()
        clickButton()
    }

    private fun clickButton(){
        var result="0"
        binding.lvFirstHappy.setOnClickListener {
            result="1"
            setFragmentResult("first_emotion", bundleOf("first_emotion" to result))
        }
        binding.lvFirstWhat.setOnClickListener {
            result="2"
            setFragmentResult("first_emotion", bundleOf("first_emotion" to result))
        }
        binding.lvFirstSad.setOnClickListener {
            result="3"
            setFragmentResult("first_emotion", bundleOf("first_emotion" to result))
        }

    }

    private fun initSetHeight() {
        val height = resources.displayMetrics.heightPixels * 0.5
        val bottomSheet = binding.clRemindFirstBottomsheet
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    private fun initClose(){
        binding.ivClose.setOnSingleClickListener {
            dialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}