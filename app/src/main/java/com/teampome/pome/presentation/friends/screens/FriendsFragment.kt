package com.teampome.pome.presentation.friends.screens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.skydoves.balloon.balloon
import com.teampome.pome.R
import com.teampome.pome.data.FriendService
import com.teampome.pome.data.remote.response.ResponseFriendsAll
import com.teampome.pome.data.remote.response.ResponseFriendsProflie
import com.teampome.pome.databinding.FragmentFriendsBinding
import com.teampome.pome.presentation.friends.FriendsProfileData
import com.teampome.pome.presentation.friends.adapters.FriendsConsumeAdapter
import com.teampome.pome.presentation.friends.adapters.FriendsProfileAdapter
import com.teampome.pome.presentation.friends.adapters.FriendsReactAdapter
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.FriendsConsumeItemDecorator
import com.teampome.pome.util.decorate.FriendsProfileItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private lateinit var friendsConsumeAdapter: FriendsConsumeAdapter
    private lateinit var friendsProfileAdapter: FriendsProfileAdapter
    private lateinit var friendsReactAdapter: FriendsReactAdapter

    @Inject
    lateinit var service: FriendService
    private val friendsBottomSheetFragment = FriendsBottomSheetFragment()
    private val friendsEmojiBalloon by balloon<FriendsEmojiBalloon>()
    private var emoji_position: Int = -1
    private var list_position: Int = -1
    private lateinit var emojiList: List<ImageView>
    var friendsData = listOf<FriendsProfileData.friends>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConsumeAdapter()
        initListAdapter()
        //어댑터 생성

        initWholeData()
        initFriendProfile()
        initFriendsData(0)
        profileClick()
        //서버통신코드

        consumeClick()
        addFriendsDecoration()

        initBalloonList()
        //balloon에 있는 이모지들 초기화
    }

    private fun initFriendsData(pos:Int) {
        lifecycleScope.launch {
            runCatching {
                service.getFriendsRecords(pos)
            }.onSuccess {
                Log.d(TAG,"FriendsFragment - initFriendsData() called success, id=${friendsProfileAdapter.clickedId},it=$it")
                val data = it.data
                if (data!!.isEmpty()) //기록이 없는 경우
                    noFriendsRecords()
                else
                    getFriendsData(data)
            }.onFailure {
                Timber.d("$it")
            }
        }

    }

    private fun initFriendProfile() {
        lifecycleScope.launch {
            kotlin.runCatching {
                service.getAllFriends()
            }.onSuccess {
                val data = it.data
                if (data.isNullOrEmpty()) //친구가 없는 경우
                    noFriendsProfile()
                else {
                    getFriendProfileData(data)
                }
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initConsumeAdapter() {
        friendsConsumeAdapter = FriendsConsumeAdapter(requireContext())
        binding.rcvFriendsconsumelist.adapter = friendsConsumeAdapter
    }

    private fun initListAdapter() {
        friendsProfileAdapter = FriendsProfileAdapter()
        binding.rcvFriends.adapter = friendsProfileAdapter
    }

    private fun getFriendsData(data: List<ResponseFriendsAll>) {
        friendsConsumeAdapter.friendConsumeList.clear()
        Log.d(TAG,"FriendsFragment - getFriendsData() called, data=$data")
        friendsConsumeAdapter.friendConsumeList.addAll(
            data.toMutableList()
        )
        friendsConsumeAdapter.notifyDataSetChanged()
        showFriendsRecord()
        binding.rcvFriendsconsumelist.visibility=View.VISIBLE
    }

    private fun getFriendProfileData(data: List<ResponseFriendsProflie>) {
        friendsProfileAdapter.friendsReponseList.addAll(
            data.toMutableList()
        )
        friendsProfileAdapter.notifyDataSetChanged()
        //Log.d(TAG,"FriendsFragment - getFriendProfileData() called data=$data")
    }

    private fun initWholeData() {
//        friendsProfileAdapter.friendsProfileList.add(
//            FriendsProfileData(FriendsProfileData.friends("전체뿡", "tmp"))
//        )
        friendsProfileAdapter.friendsReponseList.add(
            ResponseFriendsProflie(-1, "전체","tmp")
        )
    }

    private fun noFriendsProfile() {
        //friendsProfileAdapter.isEmpty = true
    }

    private fun noFriendsRecords() {
        friendsConsumeAdapter.friendConsumeList.clear()
        friendsConsumeAdapter.notifyDataSetChanged()
        binding.clFriendsempty.visibility = View.VISIBLE
    }
    private fun showFriendsRecord(){
        binding.clFriendsempty.visibility=View.INVISIBLE
    }

    private fun addFriendsDecoration() {
        binding.rcvFriendsconsumelist.addItemDecoration(FriendsConsumeItemDecorator(12))
        binding.rcvFriends.addItemDecoration(FriendsProfileItemDecorator(18))
    }

    private fun initBalloonList() {
        emojiList = listOf<ImageView>(
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_heart),
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_smile),
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_fun),
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_flex),
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_what),
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_sad)
        )
    }

    private fun addEmoji(list_pos: Int) {
        emoji_position = -1
        for (i in emojiList.indices) {
            emojiList[i].setOnClickListener {
                emoji_position = i
                friendsConsumeAdapter.getEmojiPosition(emoji_position + 1, list_position)
                friendsEmojiBalloon.dismiss()
            }
        }
    }

    private fun profileClick(){
        friendsProfileAdapter.setOnProfileListClickListener(object:
        FriendsProfileAdapter.FriendsListClickInterface{
            override fun onProfileListClick(pos: Int) {
                initFriendsData(pos)
            }
        })
    }

    private fun consumeClick() {
        friendsConsumeAdapter.setConsumeListClickListener(object :
            FriendsConsumeAdapter.FriendsConsumeListInterface {
            override fun onClick(data: View, position: Int, addEmoji: Boolean) {
                //bottom sheet로 반응 나오게 하기
                if (!addEmoji) {
                    if (!friendsBottomSheetFragment.isAdded)
                        friendsBottomSheetFragment.show(
                            childFragmentManager,
                            friendsBottomSheetFragment.tag
                        )//클릭한 부분이 다르기 때문에 addEmoji로 구분함.
                } else {
                    list_position = position
                    friendsEmojiBalloon.showAlignBottom(data)
                    addEmoji(list_position)
                    //friendsEmojiBalloon.dismiss()
                }//balloon을 띄우기 위해 현재 클릭된 data의 위치를 알려주고 해당 위치 밑에 balloon을 띄운다.
            }
        })
    }
}