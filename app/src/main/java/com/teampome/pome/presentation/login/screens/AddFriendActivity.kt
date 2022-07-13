package com.teampome.pome.presentation.login.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityAddFriendBinding
import com.teampome.pome.presentation.login.AddFriendAdapter
import com.teampome.pome.presentation.login.AddFriendData

class AddFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendBinding
    private var addFriendAdapter = AddFriendAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        binding.rcvFriend.adapter = addFriendAdapter
        addFriendList()
    }

    private fun addFriendList() {
        addFriendAdapter.submitList(
            listOf(
                AddFriendData(
                    R.drawable.ic_friend_profile, "미미"
                ),
                AddFriendData(
                    R.drawable.ic_friend_profile, "미미짱"
                ),
                AddFriendData(
                    R.drawable.ic_friend_profile, "미미파"
                ),
                AddFriendData(
                    R.drawable.ic_friend_profile, "미미리"
                ),
                AddFriendData(
                    R.drawable.ic_friend_profile, "미미쨩"
                ),
                AddFriendData(
                    R.drawable.ic_friend_profile, "미미공주"
                ),



            )
        )
    }
}