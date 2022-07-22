package com.teampome.pome.presentation.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.teampome.pome.R
import com.teampome.pome.data.FriendService
import com.teampome.pome.databinding.FragmentMyPageBinding
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.VerticalItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    @Inject
    lateinit var myService: FriendService

    private var marshMellowAdapter = MarshMellowAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initAdapter()
        showUser()
    }

    private fun initAdapter() {
        binding.rcvMarsh.adapter = marshMellowAdapter
        addMarshList()

    }

    private fun showUser() {
        lifecycleScope.launch {
            kotlin.runCatching {
                myService.getMyInfo()
            }.onSuccess {
                val data = it.data
                binding.user = data
                binding.ivProfile.load(data!!.profileImage)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }


    private fun addMarshList() {
        marshMellowAdapter.submitList(
            listOf(
                MarshMellowData(
                    R.drawable.ic_level3, R.drawable.ic_record_level, R.drawable.marshmallow_level_3_pink
                ),
                MarshMellowData(
                    R.drawable.ic_tag_level, R.drawable.ic_level_badge, R.drawable.marshmallow_level_2_blue
                ),
                MarshMellowData(
                    R.drawable.ic_tag_level, R.drawable.ic_levelup, R.drawable.marshmallow_level_2_yellow
                )
            )
        )

    }
}


/*
*/
