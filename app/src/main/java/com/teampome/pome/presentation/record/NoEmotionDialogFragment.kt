package com.teampome.pome.presentation.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentNoEmotionDialogBinding

class NoEmotionDialogFragment : DialogFragment() {

    private var _binding: FragmentNoEmotionDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoEmotionDialogBinding.inflate(layoutInflater, container, false)
        cancelDialog()
        return binding.root
    }

    private fun cancelDialog() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
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