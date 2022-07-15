package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityAddFriendBinding
import com.teampome.pome.presentation.login.AddFriendAdapter
import com.teampome.pome.presentation.login.AddFriendData
import com.teampome.pome.presentation.main.MainActivity

class AddFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendBinding
    private var addFriendAdapter = AddFriendAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        goToMainActivity()
    }

    private fun initAdapter() {
        binding.rcvFriend.adapter = addFriendAdapter
        addFriendList()
    }

    private fun goToMainActivity() {
        binding.btnComplete.setOnClickListener {
            Intent(this, MainActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }
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
                )
            )
        )
    }
}