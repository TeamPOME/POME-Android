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
import com.teampome.pome.util.setOnSingleClickListener
import com.teampome.pome.util.showToast
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
    private var startEmotion = 0
    private var endEmotion = 0
    private val reactionBottomSheet = RemindReactionBottomSheet()

    @Inject
    lateinit var service: RemindService
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        initRemindGoal()

        initRemindConsumeAdapter()
        initAdapterDecoration()

        initFirstBottomSheet()
        initSecondBottomSheet()
        initClickReset()

        reactClick()

        initFirstEmotion()
        initSecondEmotion()
    }

    private fun initFirstEmotion() {
        childFragmentManager.setFragmentResultListener(
            "first_emotion",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getString("first_emotion")
            binding.ivFirstEmotion.visibility = View.GONE
            binding.tvFirstEmotion.visibility = View.VISIBLE
            binding.tvFirstEmotion.text = emotionToString(result!!)
            startEmotion=result.toInt()
            initRemindData(startEmotion, endEmotion)
            firstBottomSheet.dismiss()
        }
    }

    private fun initSecondEmotion(){
        childFragmentManager.setFragmentResultListener(
            "second_emotion",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getString("second_emotion")
            binding.ivSecondEmotion.visibility = View.GONE
            binding.tvSecondEmotion.visibility = View.VISIBLE
            binding.tvSecondEmotion.text = emotionToString(result!!)
            endEmotion=result.toInt()
            initRemindData(startEmotion, endEmotion)
            secondBottomSheet.dismiss()
        }
    }

    private fun emotionToString(emotion: String): String {
        return when (emotion) {
            "1" -> getString(R.string.remind_happy)
            "2" -> getString(R.string.remind_idk)
            "3" -> getString(R.string.remind_regret)
            else -> ""
        }
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
                } else {
                    setGoals(data)
                    initNotEmpty(data[clickedChipPos].message, data[clickedChipPos].isPublic)
                }
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initRemindData(startEmotion: Int, endEmotion: Int) {
        lifecycleScope.launch {
            runCatching {
                service.getRemindData(clickedChipId, startEmotion, endEmotion)
            }.onSuccess {
                val data = it.data
                if (data!!.isEmpty()){
                    setEmptyGoal()
                }
                else {
                    binding.clRemindEmpty.visibility=View.GONE
                    binding.rvRemind.visibility = View.VISIBLE
                    remindConsumeAdapter.remindConsumeList.clear()
                    remindConsumeAdapter.remindConsumeList.addAll(data.toMutableList())
                    remindConsumeAdapter.notifyDataSetChanged()
                }
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initNotEmpty(goal_msg: String, isPublic: Boolean) {
        binding.clRemindEmpty.visibility = View.INVISIBLE
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

    private fun initFirstBottomSheet() {
        binding.clFirstEmotion.setOnSingleClickListener {
            if (!firstBottomSheet.isAdded)
                firstBottomSheet.show(
                    childFragmentManager, firstBottomSheet.tag
                )
        }
    }

    private fun initSecondBottomSheet() {
        binding.clSecondEmotion.setOnSingleClickListener {
            if (!secondBottomSheet.isAdded)
                secondBottomSheet.show(
                    childFragmentManager, secondBottomSheet.tag
                )
        }
    }

    private fun visibleEmotion() {
        binding.ivFirstEmotion.visibility = View.VISIBLE
        binding.ivSecondEmotion.visibility = View.VISIBLE

        binding.tvFirstEmotion.visibility = View.GONE
        binding.tvSecondEmotion.visibility = View.GONE
    }

    private fun initClickReset() {
        binding.ivReset.setOnSingleClickListener {
            wholeEmotionZero()
            initRemindData(startEmotion = 0, endEmotion = 0)
            context?.showToast("초기화되었습니다.")
            visibleEmotion()
        }
    }

    private fun setEmptyGoal() {
        binding.clRemindEmpty.visibility = View.VISIBLE
        binding.rvRemind.visibility = View.GONE
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
                    initNotEmpty(data[clickedChipPos].message, data[clickedChipPos].isPublic)
                }
                if (index == 0) {
                    clickedChipPos = 0
                    isChecked = true
                    clickedChipId = tag.toString().toInt()
                    wholeEmotionZero()
                    initRemindData(startEmotion = 0, endEmotion = 0)
                }
            }
            binding.cgGoals.addView(chip)
            binding.cgGoals.isSingleSelection = true


        }
    }

    private fun clickChip(chip_id: Int) {
        clickedChipId = chip_id
        wholeEmotionZero()
        initRemindData(startEmotion = 0, endEmotion = 0)
    }

    private fun reactClick() {
        remindConsumeAdapter.setReactionClickListener(object :
            RemindConsumeAdapter.ReactionClickListener {
            override fun onClick(data: View, pos: Int, recordId: Int) {
                if (!reactionBottomSheet.isAdded) {
                    val bundle = Bundle().apply {
                        putString("recordId", recordId.toString())
                    }
                    reactionBottomSheet.arguments = bundle
                    reactionBottomSheet.show(
                        childFragmentManager,
                        reactionBottomSheet.tag
                    )
                }
            }
        })
    }

    private fun wholeEmotionZero(){
        startEmotion=0
        endEmotion=0
    }
}