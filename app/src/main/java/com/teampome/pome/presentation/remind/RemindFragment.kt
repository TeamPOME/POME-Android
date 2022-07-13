package com.teampome.pome.presentation.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData
import com.teampome.pome.util.BaseFragment

class RemindFragment : BaseFragment<FragmentRemindBinding>(R.layout.fragment_remind) {
    private lateinit var remindConsumeAdapter: RemindConsumeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRemindConsumeAdapter()

        //initAddRemindConsume()
    }

    private fun initRemindConsumeAdapter(){
        remindConsumeAdapter= RemindConsumeAdapter()
        binding.rvRemind.adapter=remindConsumeAdapter
    }

    private fun initAddRemindConsume(){
        remindConsumeAdapter.submitList(
            listOf(
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713"),
                FriendsConsumeData(name="dwd", description = "와플은 맛잇다", price = "3900", tag = "간식", first_emotion = 1, second_emotion = 2, date = "0713")

            )
        )
    }
}