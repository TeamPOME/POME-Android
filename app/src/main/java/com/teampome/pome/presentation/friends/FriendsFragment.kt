package com.teampome.pome.presentation.friends

import android.os.Bundle
import android.view.View
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentFriendsBinding
import com.teampome.pome.util.BaseFragment

class FriendsFragment : BaseFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private lateinit var friendsConsumeAdapter: FriendsConsumeAdapter
    private lateinit var friendsProfileAdapter: FriendsProfileAdapter

    private val friendsConsumeData = mutableListOf<FriendsConsumeData>()
    private val friendsProfileData = mutableListOf<FriendsProfileData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConsumeAdapter()
        initListAdapter()
        getFriendsConsumeData()
        getFriendProfileList()
    }

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
                    name = "ㅇㅈㅇ",
                    description = "일이삼사오육칠팔구십일이삼사오육칠팔구",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱"
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ",
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

    private fun getFriendProfileList() {
        friendsProfileAdapter.submitList(
            listOf(
                FriendsProfileData("황연진","tmp"),
                FriendsProfileData("김수빈","tmp"),
                FriendsProfileData("양지영","tmp"),
                FriendsProfileData("황연진","tmp"),
                FriendsProfileData("김수빈","tmp"),
                FriendsProfileData("양지영","tmp"),
                FriendsProfileData("황연진","tmp"),
                FriendsProfileData("김수빈","tmp"),
                FriendsProfileData("양지영","tmp")
            )
        )
    }

}