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
    private val firstBottomSheet = RemindFirstBottomSheetFragment()
    private val secondBottomSheet = RemindSecondBottomSheetFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRemindConsumeAdapter()
        initAdapterDecoration()
        initAddRemindConsume()

        initClickFirstEmotion()
        initClickSecondEmotion()
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

    private fun initClickFirstEmotion() {
        binding.ivFirstEmotion.setOnClickListener {
            if (!firstBottomSheet.isAdded)
                firstBottomSheet.show(
                    childFragmentManager, firstBottomSheet.tag
                )
        }
    }

    private fun initClickSecondEmotion() {
        binding.ivSecondEmotion.setOnClickListener {
            if (!secondBottomSheet.isAdded)
                secondBottomSheet.show(
                    childFragmentManager, secondBottomSheet.tag
                )
        }
    }
}