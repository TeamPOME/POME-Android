package com.teampome.pome.presentation.login.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Response.error

class SignUpBottomSheet : Fragment() {
    private var _binding: SignUpBottomSheet? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpBottomSheet.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
    }

    private fun initSetHeight() {
        //360으로
        val bottomSheet = binding.clWholebottomsheet
        bottomSheet.minHeight = 360
        bottomSheet.maxHeight = 360
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
