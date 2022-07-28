package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentRemindSecondBottomSheetBinding

class RemindSecondBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindSecondBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindSecondBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
        initClose()
        clickButton()
    }

    private fun initSetHeight() {
        val height = resources.displayMetrics.heightPixels * 0.5
        val bottomSheet = binding.clRemindSecondBottomsheet
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    private fun initClose() {
        binding.ivClose.setOnClickListener {
            dialog?.dismiss()
        }
    }
    private fun clickButton(){
        var result="0"
        binding.lvSecondHappy.setOnClickListener {
            result="1"
            setFragmentResult("second_emotion", bundleOf("second_emotion" to result))
        }
        binding.lvSecondWhat.setOnClickListener {
            result="2"
            setFragmentResult("second_emotion", bundleOf("second_emotion" to result))
        }
        binding.lvSecondSad.setOnClickListener {
            result="3"
            setFragmentResult("second_emotion", bundleOf("second_emotion" to result))
        }

    }
}