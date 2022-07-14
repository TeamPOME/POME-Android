package com.teampome.pome.presentation.remind.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.material.chip.Chip
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRemindBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData
import com.teampome.pome.presentation.remind.RemindConsumeAdapter
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.FriendsConsumeItemDecorator

class RemindFragment : BaseFragment<FragmentRemindBinding>(R.layout.fragment_remind) {
    private lateinit var remindConsumeAdapter: RemindConsumeAdapter
    private val firstBottomSheet = RemindFirstBottomSheetFragment()
    private val secondBottomSheet = RemindSecondBottomSheetFragment()
    private val remindList = mutableListOf<FriendsConsumeData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRemindConsumeAdapter()
        initAdapterDecoration()
        //initAddRemindConsume()

        initClickFirstEmotion()
        initClickSecondEmotion()
        initClickReset()

        getGoal()
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

    private fun initClickReset() {
        binding.ivReset.setOnClickListener {
            //서버통신할 때 submitList로  다시 reset
        }
    }

    private fun getGoal() {
        //if()- goal이 없는 경우
        //return setEmptyGoal()
        //if()- goal만 있는 경우
        //return setNotEmptyGoal()
        //if()- goal과 기록 다 있는 경우
        return setRemindList()
    }

    private fun setEmptyGoal() {
        //chipNoGoal(), recyclerview은 안보임,
        binding.chipNogoal.visibility = View.VISIBLE
        binding.clRemindEmpty.visibility = View.VISIBLE
        binding.ivLockCheck.setImageResource(R.drawable.ic_empty_goal)
        binding.tvGoal.setText(R.string.remind_nogoal_msg)
        binding.tvGoal.setTextColor(Color.GRAY)
    }

    private fun setNotEmptyGoal() {
        //chip, 목표 이름 가져오기
        val goal_tag: String = "커피"
        //받아온 목표
        val goal_des = "하루 한잔만 마시기"
        binding.chipNogoal.visibility = View.GONE

        val chip = Chip(context)
        chip.text=goal_tag

        binding.cgGoals.addView(chip)

        binding.clRemindEmpty.visibility = View.VISIBLE
        binding.ivLockCheck.setImageResource(R.drawable.ic_unlock)
        binding.tvGoal.text=goal_des
        binding.tvGoal.setTextColor(Color.BLACK)
    }

    private fun setRemindList() {
        binding.chipNogoal.visibility = View.GONE
        binding.rvRemind.visibility = View.VISIBLE
        binding.clRemindEmpty.visibility = View.INVISIBLE
        initAddRemindConsume()
        val goal_tag: String = "커피"
        //받아온 목표
        val goal_des = "하루 한잔만 마시기"
        binding.chipNogoal.visibility = View.INVISIBLE

        val chip = Chip(context)
        chip.setText(goal_tag)
        binding.cgGoals.addView(chip)

        binding.ivLockCheck.setImageResource(R.drawable.ic_unlock)
        binding.tvGoal.setText(goal_des)
        binding.tvGoal.setTextColor(Color.BLACK)
    }
}