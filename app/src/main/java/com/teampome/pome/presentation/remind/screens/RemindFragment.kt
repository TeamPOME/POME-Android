package com.teampome.pome.presentation.remind.screens

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
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
    private val reactionBottomSheet=RemindReactionBottomSheet()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRemindConsumeAdapter()
        initAdapterDecoration()
        //initAddRemindConsume()

        initClickFirstEmotion()
        initClickSecondEmotion()
        initClickReset()

        getGoal()

        reactClick()
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
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
                ),
                FriendsConsumeData(
                    name = "dwd",
                    description = "와플은 맛잇다",
                    price = "3900",
                    tag = "간식",
                    first_emotion = 1,
                    second_emotion = 2,
                    date = "0713",
                    reaction = listOf(1,2,3)
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
        //chipNoGoal(), recyclerview은 안보임
        binding.cNogoal.visibility = View.VISIBLE
        binding.clRemindEmpty.visibility = View.VISIBLE
        binding.ivLockCheck.setImageResource(R.drawable.ic_empty_goal)
        binding.tvGoal.setText(R.string.remind_nogoal_msg)
        binding.tvGoal.setTextColor(Color.GRAY)
    }

    private fun setNotEmptyGoal() {
        //chip, 목표 이름 가져오기
        val goal_tag = "커피"
        //받아온 목표
        val goal_des = "하루 한잔만 마시기"
        binding.cNogoal.visibility = View.VISIBLE

        val chip1 = Chip(context).apply {
            text = goal_tag
            id = R.id.chip_1
        }
        val chip2 = Chip(context).apply {
            text = goal_tag
            id = R.id.chip_1
        }
        binding.cgGoals.addView(chip1, 0)
        binding.cgGoals.addView(chip2, 1)

        binding.clRemindEmpty.visibility = View.VISIBLE
        binding.ivLockCheck.setImageResource(R.drawable.ic_unlock)
        binding.tvGoal.apply {
            text = goal_des
            setTextColor(Color.BLACK)
        }
    }

    private fun setRemindList() {
        binding.cNogoal.visibility = View.GONE
        binding.rvRemind.visibility = View.VISIBLE
        binding.clRemindEmpty.visibility = View.INVISIBLE
        initAddRemindConsume()
        val goal_tag = "커피"
        //받아온 목표
        val goal_des = "하루 한잔만 마시기"

        val drawable =
            ChipDrawable.createFromAttributes(
                requireContext(),
                null,
                0,
                R.style.ChipTextStyleSelected
            )

        val chip1 = Chip(context).apply {
            text = "1번 칩"
            isCheckable=true
            isCheckedIconVisible=false
            id = R.id.c_remind0
            chipBackgroundColor = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                ),
                intArrayOf(Color.WHITE, ContextCompat.getColor(context,R.color.pome_main))
            )

            //텍스트
            setTextColor(
                ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_checked),
                        intArrayOf(android.R.attr.state_checked)
                    ),
                    intArrayOf(Color.BLACK, Color.WHITE)
                )
            )
        }
        val chip2 = Chip(context).apply {
            text = "2번 칩"
            isCheckable=true
            isCheckedIconVisible=false
            id = R.id.c_remind1
            chipBackgroundColor = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_checked)
                ),
                intArrayOf(Color.WHITE, ContextCompat.getColor(context,R.color.pome_main))
            )

            //텍스트
            setTextColor(
                ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_checked),
                        intArrayOf(android.R.attr.state_checked)
                    ),
                    intArrayOf(Color.BLACK, Color.WHITE)
                )
            )
        }

        binding.cgGoals.addView(chip1)
        binding.cgGoals.addView(chip2)
        binding.cgGoals.isSingleSelection=true
        chip1.isChecked=true

        binding.ivLockCheck.setImageResource(R.drawable.ic_unlock)
        binding.tvGoal.apply {
            text = goal_des
            setTextColor(Color.BLACK)
        }
    }
    fun reactClick(){
        remindConsumeAdapter.setReactionClickListener(object:RemindConsumeAdapter.ReactionClickListener{
            override fun onClick(data: View, pos: Int) {
                //친구들이 단 이모지 클릭하면 혹시 무슨 일이 있는지 알아보
                if(!reactionBottomSheet.isAdded)
                    reactionBottomSheet.show(
                        childFragmentManager,
                        reactionBottomSheet.tag
                    )
            }
        })
    }
}