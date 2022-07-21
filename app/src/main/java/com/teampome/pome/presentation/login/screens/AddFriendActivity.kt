package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.teampome.pome.R
import com.teampome.pome.data.FriendService
import com.teampome.pome.data.remote.request.RequestFriendsData
import com.teampome.pome.data.remote.response.ResponseFriendsData
import com.teampome.pome.databinding.ActivityAddFriendBinding
import com.teampome.pome.presentation.login.AddFriendAdapter
import com.teampome.pome.presentation.login.AddFriendData
import com.teampome.pome.presentation.main.MainActivity
import com.teampome.pome.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AddFriendActivity : AppCompatActivity() {
    @Inject
    lateinit var friendService: FriendService

    private lateinit var binding: ActivityAddFriendBinding
    private var addFriendAdapter = AddFriendAdapter(::addFriend)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        showFriendList()
        goToMainActivity()
    }

    private fun initAdapter() {
        binding.rcvFriend.adapter = addFriendAdapter
    }

    private fun goToMainActivity() {
        binding.btnComplete.setOnSingleClickListener {
            Intent(this, MainActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }
    }

    private fun showFriendList() {
        binding.btnSearch.setOnClickListener {
            lifecycleScope.launch {
                runCatching {
                    friendService.getAddFriends(binding.etNickname.text.toString())
                }.onSuccess {
                    binding.rcvFriend.isVisible = it.data!!.isNotEmpty()
                    binding.clNoNickname.isVisible = it.data.isNullOrEmpty()
                    if (it.data.isNotEmpty()) {
                        addFriendAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun addFriend(friend: ResponseFriendsData) {
        var position = friend.id
        addFriendResult(position)
    }

    private fun addFriendResult(position: Int) {
        lifecycleScope.launch {
            runCatching {
                friendService.setAddFriends(RequestFriendsData(position))
            }.onSuccess {
                Timber.e("success add friends")
            }.onFailure {
                Timber.e("failure add friends")
            }
        }
    }

}
