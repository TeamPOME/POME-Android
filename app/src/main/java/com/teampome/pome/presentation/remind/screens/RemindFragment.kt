package com.teampome.pome.presentation.remind.screens

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.teampome.pome.R
import com.teampome.pome.data.RemindService
import com.teampome.pome.data.remote.response.ResponseRemindGoal
import com.teampome.pome.databinding.FragmentRemindBinding
import com.teampome.pome.presentation.remind.RemindConsumeAdapter
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.FriendsConsumeItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RemindFragment : BaseFragment<FragmentRemindBinding>(R.layout.fragment_remind) {
    private lateinit var remindConsumeAdapter: RemindConsumeAdapter
    private val firstBottomSheet = RemindFirstBottomSheetFragment()
    private val secondBottomSheet = RemindSecondBottomSheetFragment()
    private var clickedChipId = -1
    private var clickedChipPos = -1
    private val reactionBottomSheet = RemindReactionBottomSheet()

    @Inject
    lateinit var service: RemindService
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRemindGoal()
        //initRemindData()
        //서버통신

        initRemindConsumeAdapter()
        initAdapterDecoration()

        initClickFirstEmotion()
        initClickSecondEmotion()
        initClickReset()
        getGoal()
        reactClick()
    }

    private fun initRemindGoal() {
        lifecycleScope.launch {
            runCatching {
                service.getRemindGoal()
            }.onSuccess {
                val data = it.data
                if (data!!.isEmpty()) {
                    binding.cNogoal.visibility = View.VISIBLE
                    binding.clRemindEmpty.visibility = View.VISIBLE
                    //setEmptyGoal()
                } else {
                    setGoals(data)
                    //setGoalMessage(data)
                }
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initRemindData() {
        lifecycleScope.launch {
            runCatching {
                service.getRemindData(clickedChipId, 0, 0)
            }.onSuccess {
                val data = it.data
                if (data!!.isEmpty())
                    setEmptyGoal()
                else {
                    binding.rvRemind.visibility = View.VISIBLE
                    initNotEmpty(
                        data[clickedChipPos].goalMessage,
                        data[clickedChipPos].isGoalPublic
                    )
                    remindConsumeAdapter.submitList(data)
                }
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initNotEmpty(goal_msg: String, isPublic: Boolean) {
        binding.clRemindEmpty.visibility = View.INVISIBLE
        Log.d(TAG, "RemindFragment - initNotEmpty() called, goal_msg:$goal_msg, isPublic=$isPublic")
        if (isPublic == true) {
            binding.ivLockCheck.setImageResource(R.drawable.ic_unlock)
        } else {
            binding.ivLockCheck.setImageResource(R.drawable.ic_lock_all)
        }
        binding.tvGoal.text = goal_msg

    }

    private fun initAdapterDecoration() {
        binding.rvRemind.addItemDecoration(FriendsConsumeItemDecorator(14))
    }

    private fun initRemindConsumeAdapter() {
        remindConsumeAdapter = RemindConsumeAdapter()
        binding.rvRemind.adapter = remindConsumeAdapter
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
            initRemindData()
            //goal_id 넣어주기
        }
    }

    private fun getGoal() {
        //if()- goal이 없는 경우
        //return setEmptyGoal()
        //if()- goal만 있는 경우
        //return setNotEmptyGoal()
        //if()- goal과 기록 다 있는 경우
        //return setRemindList()
    }

    private fun setEmptyGoal() {
        //chipNoGoal(), recyclerview은 안보임

        binding.ivLockCheck.setImageResource(R.drawable.ic_empty_goal)
        binding.tvGoal.setText(R.string.remind_nogoal_msg)
        binding.tvGoal.setTextColor(Color.GRAY)
        binding.rvRemind.visibility = View.INVISIBLE
    }

    private fun setGoals(data: List<ResponseRemindGoal>) {
        binding.cNogoal.visibility = View.GONE
        data.forEachIndexed { index, responseRemindGoal ->
            val chip = Chip(context).apply {
                text = responseRemindGoal.category
                isCheckable = true
                isCheckedIconVisible = false
                tag = responseRemindGoal.id.toString()

                chipBackgroundColor = ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_checked),
                        intArrayOf(android.R.attr.state_checked)
                    ),
                    intArrayOf(Color.WHITE, ContextCompat.getColor(context, R.color.pome_main))
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
                setOnClickListener {
                    clickedChipPos = index
                    clickChip(tag.toString().toInt())
                }
            }
            binding.cgGoals.addView(chip)
            binding.cgGoals.isSingleSelection = true
            if (index == 0) {
                clickedChipPos = 0
                chip.isChecked = true
                clickedChipId = chip.tag.toString().toInt()
                initRemindData()
            }

        }
    }

    private fun clickChip(chip_id: Int) {
        clickedChipId = chip_id
        initRemindData()
    }

    fun reactClick() {
        remindConsumeAdapter.setReactionClickListener(object :
            RemindConsumeAdapter.ReactionClickListener {
            override fun onClick(data: View, pos: Int) {
                //친구들이 단 이모지 클릭하면 혹시 무슨 일이 있는지 알아보
                if (!reactionBottomSheet.isAdded)
                    reactionBottomSheet.show(
                        childFragmentManager,
                        reactionBottomSheet.tag
                    )
            }
        })
    }
}