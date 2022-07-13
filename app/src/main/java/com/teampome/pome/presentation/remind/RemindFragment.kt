package com.teampome.pome.presentation.remind

import android.os.Bundle
import android.view.View
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData
import com.teampome.pome.util.BaseFragment
import com.teampome.pome.util.FriendsConsumeItemDecorator

class RemindFragment : BaseFragment<FragmentRemindBinding>(R.layout.fragment_remind) {
    private lateinit var remindConsumeAdapter: RemindConsumeAdapter
    private val bottomSheetFragment = RemindBottomSheetFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRemindConsumeAdapter()
        initAdapterDecoration()
        initAddRemindConsume() //서버통신할 코드
        showBottomSheet()
    }

    private fun initAdapterDecoration() {
        binding.rvRemind.addItemDecoration(FriendsConsumeItemDecorator(14))
    }

    private fun initRemindConsumeAdapter() {
        remindConsumeAdapter = RemindConsumeAdapter()
        binding.rvRemind.adapter = remindConsumeAdapter
    }

    private fun initAddRemindConsume() {
        remindConsumeAdapter.submitList(
            listOf(
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713"
                )

            )
        )
    }

    private fun showBottomSheet() {
        //서버 통신을 통해 반응이 있는 경우만 보여주기
        remindConsumeAdapter.setReactionClickListener(object :
            RemindConsumeAdapter.ReactionClickListener {
            override fun onClick(data: View, pos: Int) {
                if (!bottomSheetFragment.isAdded)
                    bottomSheetFragment.show(
                        childFragmentManager, bottomSheetFragment.tag
                    )
            }

        })

    }
}