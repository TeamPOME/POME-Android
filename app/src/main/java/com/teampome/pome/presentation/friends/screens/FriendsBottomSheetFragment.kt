package com.teampome.pome.presentation.friends.screens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.data.FriendService
import com.teampome.pome.databinding.FragmentFriendsBottomSheetBinding
import com.teampome.pome.presentation.friends.FriendReactionData
import com.teampome.pome.presentation.friends.adapters.FriendsReactAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FriendsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFriendsBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다.")
    private lateinit var friendsReactAdapter: FriendsReactAdapter

    @Inject
    lateinit var service: FriendService

    private lateinit var id:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFriendsBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
        initAdapter()

        getBundle() //recordId값 받아오기

        initReactionBottomSheet()
        //서버통신

    }

    private fun getBundle(){
        val bundle=arguments
        if(bundle!=null){
            id= bundle.getString("recordId").toString()
        }
        Log.d(TAG,"FriendsBottomSheetFragment - getBundle() called, id=$id")
    }

    private fun initAdapter(){
        friendsReactAdapter= FriendsReactAdapter()
        binding.rvFriendsReaction.adapter=friendsReactAdapter
    }
    private fun initReactionBottomSheet(){
        lifecycleScope.launch {
            runCatching {
                service.getFriendsReaction(recordId = id.toInt(),type=0)
                //TYPE=0은 전체 조회
            }.onSuccess {
                val data=it.data
                Log.d(TAG,"FriendsBottomSheetFragment - initReactionBottomSheet() called, data=$data")
                friendsReactAdapter.submitList(data)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initSetHeight() {
        //360으로
        val height = resources.displayMetrics.heightPixels * 0.5
        val bottomSheet = binding.clWholebottomsheet
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}