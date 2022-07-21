package com.teampome.pome.presentation.remind.screens

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.data.FriendService
import com.teampome.pome.databinding.FragmentRemindReactionBottomSheetBinding
import com.teampome.pome.presentation.friends.FriendReactionData
import com.teampome.pome.presentation.remind.RemindReactionData
import com.teampome.pome.presentation.remind.adapters.RemindReactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RemindReactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindReactionBottomSheetBinding?=null
    private val binding get() = _binding?:error("binding이 되지 않는다.")
    private lateinit var remindReactionAdapter:RemindReactionAdapter

    @Inject
    lateinit var service:FriendService
    lateinit var id:String

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

        getBundle()
        initRemindBottomSheet()
    }
    private fun getBundle(){
        val bundle=arguments
        if(bundle!=null){
            id=bundle.getString("recordId").toString()
        }
    }
    private fun initRemindBottomSheet(){
        lifecycleScope.launch {
            runCatching {
                service.getFriendsReaction(recordId = id.toInt(),type=0)
                //TYPE=0은 전체 조회
            }.onSuccess {
                Log.d(TAG,"RemindReactionBottomSheet - initRemindBottomSheet() called id=$id")
                val data=it.data?.reactions
                binding.tvWhole.text="전체 "+it.data?.total
                remindReactionAdapter.submitList(data)
            }.onFailure {
                Timber.d("$it")
            }
        }
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