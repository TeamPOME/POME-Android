package com.teampome.pome.presentation.friends

import android.os.Bundle
import android.view.View
import com.skydoves.balloon.balloon
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentFriendsBinding
import com.teampome.pome.util.BaseFragment
import com.teampome.pome.util.FriendsConsumeItemDecorator
import com.teampome.pome.util.FriendsProfileItemDecorator

class FriendsFragment : BaseFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private lateinit var friendsConsumeAdapter: FriendsConsumeAdapter
    private lateinit var friendsProfileAdapter: FriendsProfileAdapter
    private val friendsBottomSheetFragment = FriendsBottomSheetFragment()
    private val friendsEmojiBalloon by balloon<FriendsEmojiBalloon>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConsumeAdapter()

        initListAdapter()

        //후에 서버통신 할 에정
        initWholeData()

        getFriendProfileList()
        getFriendsConsumeData()

        initConsumeClick()
        addFriendsDecoration()
    }

    // 친구 프로필 리스트와 소비 리스트 한번에 보이고 안보이게


    private fun initConsumeAdapter() {
        friendsConsumeAdapter = FriendsConsumeAdapter()
        binding.rcvFriendsconsumelist.adapter = friendsConsumeAdapter
    }

    private fun initListAdapter() {
        friendsProfileAdapter = FriendsProfileAdapter()
        binding.rcvFriends.adapter = friendsProfileAdapter
    }

    private fun getFriendsConsumeData() {
        //추후에 서버에서 가져오기
        binding.rcvFriendsconsumelist.visibility = View.VISIBLE
        binding.clFriendsempty.visibility = View.INVISIBLE
        friendsConsumeAdapter.submitList(
            listOf(
                FriendsConsumeData(
                    name = "ㅇㅈㅇ12",
                    description = "일이삼사오육칠팔구십일이삼사오육칠팔구",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ2",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ3",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ4",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ5",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ6",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ7",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ8",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ9",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                )
            )
        )
    }

    private fun initWholeData() {
        friendsProfileAdapter.friendsProfileList.add(
            FriendsProfileWholeData("전체", "tmp")
        )
    }

    private fun getFriendProfileList() {
        binding.rcvFriends.visibility = View.VISIBLE
        binding.clFriendsempty.visibility = View.INVISIBLE
        binding.ivEmptyfriends.setImageResource(R.drawable.ic_friend_profile_full)
        friendsProfileAdapter.friendsProfileList.addAll(
            listOf(
                FriendsProfileData("황연진입니다", "tmp"),
                FriendsProfileData("김수빈", "tmp"),
                FriendsProfileData("양지영", "tmp"),
                FriendsProfileData("황연진", "tmp"),
                FriendsProfileData("김수빈", "tmp"),
                FriendsProfileData("양지영", "tmp"),
                FriendsProfileData("황연진", "tmp"),
                FriendsProfileData("김수빈", "tmp"),
                FriendsProfileData("양지영", "tmp")
            )
        )
    }

    private fun addFriendsDecoration() {
        binding.rcvFriendsconsumelist.addItemDecoration(FriendsConsumeItemDecorator(12))
        binding.rcvFriends.addItemDecoration(FriendsProfileItemDecorator(18))
    }

//    private fun initProfileClick(){
//        friendsProfileAdapter.setOnProfileListClickListener(object:FriendsProfileAdapter.FriendsListClickInterface{
//            override fun onProfileListClick(v: View, data: FriendsProfileData, pos: Int) {
//                bininding.rcv
//            }
//
//
//        })
//    }

    private fun initConsumeClick() {
        friendsConsumeAdapter.setConsumeListClickListener(object :
            FriendsConsumeAdapter.FriendsConsumeListInterface {
            override fun onClick(data: View, position: Int, addEmoji: Boolean) {
                //bottom sheet로 반응 나오게 하기
                if (!addEmoji) {
                    if (!friendsBottomSheetFragment.isAdded)
                        friendsBottomSheetFragment.show(
                            childFragmentManager,
                            friendsBottomSheetFragment.tag
                        )
                } else {
                    friendsEmojiBalloon.showAlignBottom(data)
                }

            }
        })

    }
}