package com.teampome.pome.presentation.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentFriendsBinding
import com.teampome.pome.util.BaseFragment

class FriendsFragment: BaseFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private lateinit var friendsConsumeAdapter: FriendsConsumeAdapter
    private val friendsConsumeData= mutableListOf<FriendsConsumeData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initAdapter()
            getFriendsConsumeData()
    }

    private fun initAdapter(){
        friendsConsumeAdapter= FriendsConsumeAdapter()
        binding.rcvFriendsconsumelist.adapter=friendsConsumeAdapter

    }

    private fun getFriendsConsumeData(){
        binding.rcvFriendsconsumelist.visibility=View.VISIBLE
        binding.clFriendsempty.visibility=View.INVISIBLE
        friendsConsumeAdapter.submitList(
            listOf(
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱"),
                FriendsConsumeData(name="ㅇㅈㅇ", description = "탐탐민초 추천", date = "07.09", price = "4,400", first_emotion = 1, second_emotion = 2, tag="탐탐은 민초가 짱")
            )
        )

    }

}