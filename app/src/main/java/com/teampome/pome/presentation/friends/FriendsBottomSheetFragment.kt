package com.teampome.pome.presentation.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentFriendsBottomSheetBinding
import com.teampome.pome.util.BaseFragment

class FriendsBottomSheetFragment: BottomSheetDialogFragment() {
    private var _binding: FragmentFriendsBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFriendsBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


}