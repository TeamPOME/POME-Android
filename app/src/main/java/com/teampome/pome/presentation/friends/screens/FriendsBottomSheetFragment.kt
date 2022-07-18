package com.teampome.pome.presentation.friends.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.databinding.FragmentFriendsBottomSheetBinding
import com.teampome.pome.presentation.friends.FriendReactionData
import com.teampome.pome.presentation.friends.adapters.FriendsReactAdapter

class FriendsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFriendsBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다.")
    private lateinit var friendsReactAdapter: FriendsReactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFriendsBottomSheetBinding.inflate(layoutInflater, container, false)
        initSetHeight()
        initAdapter()
        addData()
        return binding.root
    }

    private fun initAdapter(){
        friendsReactAdapter= FriendsReactAdapter()
        binding.rvFriendsReaction.adapter=friendsReactAdapter
    }
    private fun addData(){
        friendsReactAdapter.submitList(
            listOf(
                FriendReactionData(1,"양지영"),
                FriendReactionData(3,"황연진"),
                FriendReactionData(5, "김수빈"),
                FriendReactionData(3,"황연진"),
                FriendReactionData(5, "김수빈"),
                FriendReactionData(2, "김수빈"),
                FriendReactionData(4,"황연진"),
                FriendReactionData(6, "김수빈"),
                )
        )
    }

    private fun initSetHeight() {
        //360으로
        val bottomSheet = binding.clWholebottomsheet
        val height = resources.displayMetrics.heightPixels*0.5
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}