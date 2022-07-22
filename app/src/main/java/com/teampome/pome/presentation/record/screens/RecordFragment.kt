package com.teampome.pome.presentation.record.screens

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.teampome.pome.R
import com.teampome.pome.data.GoalService
import com.teampome.pome.data.RecordsService
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.presentation.record.RecordAdapter
import com.teampome.pome.presentation.record.RecordData
import com.teampome.pome.presentation.record.viewmodels.GoalIdViewModel
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.setOnSingleClickListener
import com.teampome.pome.util.setVisibility
import com.teampome.pome.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    @Inject
    lateinit var service: GoalService

    @Inject
    lateinit var recordService: RecordsService
    private lateinit var recordAdapter: RecordAdapter
    private val viewModel by activityViewModels<GoalIdViewModel>()
    private var clickedChipPos: Int = -1
    private var clickedChipId: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        deleteGoalNetwork()
        noDragSeekBar()
        initGoalChip()
        goGoalDateActivity()
        initAdapter()
        noGoalClickEvent()
        goLookBackActivity()
    }

    private fun deleteGoalNetwork() {
        viewModel.goalId.observe(viewLifecycleOwner) {
            service.deleteGoal(it).enqueueUtil(
                onSuccess = {
                    binding.cgGoal.removeAllViews()
                    initGoalChip()
                }
            )
        }
    }

    private fun goGoalDateActivity() {
        binding.apply {
            btnGoaladd.setOnClickListener {
                if (cgGoal.size >= 11) {
                    showDialog()
                } else {
                    val intent = Intent(requireContext(), GoalDateActivity::class.java)
                    startActivity(intent)
                }
            }
            btnMakegoal.setOnClickListener {
                if (cgGoal.size >= 11) {
                    showDialog()
                } else {
                    val intent = Intent(requireContext(), GoalDateActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showDialog() {
        val dialog = GoalLimitDialogFragment()
        activity?.let { it1 ->
            dialog.show(
                it1.supportFragmentManager,
                "GoalLimitDialogFragment"
            )
        }
    }

    private fun initGoalChip() {
        service.initGoalChip().enqueueUtil(
            onSuccess = {
                it.data?.forEachIndexed { index, responseGoalCreate ->
                    val chip = Chip(context).apply {
                        tag = responseGoalCreate.id.toString()
                        text = responseGoalCreate.category
                        setTextAppearanceResource(R.style.PomeSb14)
                        isCheckable = true
                        isCheckedIconVisible = false
                        chipBackgroundColor = ColorStateList(
                            arrayOf(
                                intArrayOf(-android.R.attr.state_checked),
                                intArrayOf(android.R.attr.state_checked)
                            ),
                            intArrayOf(
                                ContextCompat.getColor(context, R.color.pome_grey_0),
                                ContextCompat.getColor(context, R.color.pome_main)
                            )
                        )
                        setTextColor(
                            ColorStateList(
                                arrayOf(
                                    intArrayOf(-android.R.attr.state_checked),
                                    intArrayOf(android.R.attr.state_checked)
                                ),
                                intArrayOf(
                                    ContextCompat.getColor(context, R.color.pome_grey_5),
                                    Color.WHITE
                                )
                            )
                        )
                        setOnClickListener {
                            clickedChipPos = index
                            initGoalDetail(tag.toString().toInt())
                        }
                        if (index == 0) {
                            clickedChipPos = 0
                            isChecked = true
                            clickedChipId = tag.toString().toInt()
                            initGoalDetail(tag.toString().toInt())
                        }
                    }
                    binding.cgGoal.addView(chip)
                }
            },
            onError = {
                requireContext().showToast("불러오기에 실패했습니다.")
            }
        )
    }

    private fun initGoalDetail(goalId: Int) {
        service.initGoalDetail(goalId).enqueueUtil(
            onSuccess = {
                visibilityGoalTrue()
                binding.goaldetail = it.data
                binding.ivLock.isSelected = it.data?.isPublic ?: error("바인딩 에러")
                when (it.data.rate) {
                    999 -> {
                        val over = 290
                        binding.tvSeekbar.x = over.toFloat()
                    }
                    0 -> {
                        val start = 20
                        binding.tvSeekbar.x = start.toFloat()
                    }
                    else -> {
                        binding.tvSeekbar.x = (it.data.rate * 2.8).toFloat()
                    }
                }
                val id = it.data.id
                binding.btnMore.setOnSingleClickListener {
                    val bottomSheet = GoalDeleteBottomSheet(id)
                    bottomSheet.show(childFragmentManager, bottomSheet.tag)
                }
                initGoalRecord(goalId)
            },
            onError = {
                requireContext().showToast("불러오기에 실패했습니다.")
            }
        )
    }

    private fun visibilityGoalTrue() {
        binding.apply {
            iv3d.visibility = View.GONE
            tvNogoaltext.visibility = View.GONE
            btnMakegoal.visibility = View.GONE
            ivLock.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            btnMore.visibility = View.VISIBLE
            tvDday.visibility = View.VISIBLE
            tvAmount.visibility = View.VISIBLE
            tvUseamount.visibility = View.VISIBLE
            tvSlash.visibility = View.VISIBLE
            tvGoalamount.visibility = View.VISIBLE
            sbGoal.visibility = View.VISIBLE
            tvSeekbar.visibility = View.VISIBLE
        }
    }

    private fun noDragSeekBar() {
        binding.sbGoal.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    return false
                }
                return true
            }
        })
    }

    private fun initAdapter() {
        recordAdapter = RecordAdapter()
        binding.rvRecord.adapter = recordAdapter
        initDecoration()
    }

    private fun initDecoration() {
        binding.rvRecord.addItemDecoration(CustomItemDecorator(12))
        binding.rvRecord.addItemDecoration(VerticalItemDecorator(6))
    }

    private fun goLookBackActivity() {
        binding.clLookback.setOnClickListener {
//            val intent = Intent(requireContext(), RecordLookBackActivity::class.java)
//            startActivity(intent)
            val dialog = NoEmotionDialogFragment()
            activity?.let { it1 ->
                dialog.show(
                    it1.supportFragmentManager,
                    "NoEmotionDialogFragment"
                )
            }
        }
    }

    private fun noGoalClickEvent() {
        binding.fabWrite.setOnClickListener {
            if (binding.cgGoal.size != 0) {
                val intent = Intent(requireContext(), RecordWriteActivity::class.java)
                startActivity(intent)
            } else {
                val dialog = NoRecordDialogFragment()
                activity?.let { it1 ->
                    dialog.show(
                        it1.supportFragmentManager,
                        "NoRecordDialogFragment"
                    )
                }
            }
        }
    }

    private fun initGoalRecord(goalId: Int) {
        recordService.initGoalRecord(goalId).enqueueUtil(
            onSuccess = {
                if (it.data?.records?.size != 0) {
                    visibilityRecordTrue()
                    recordAdapter.submitList(it.data?.records)
                } else {
                    visibilityRecordFalse()
                }
            }
        )
    }

    private fun visibilityRecordTrue() {
        binding.apply {
            ivNothing.visibility = View.GONE
            tvNothing.visibility = View.GONE
            rvRecord.visibility = View.VISIBLE
        }
    }

    private fun visibilityRecordFalse() {
        binding.apply {
            ivNothing.visibility = View.VISIBLE
            tvNothing.visibility = View.VISIBLE
            rvRecord.visibility = View.GONE
        }
    }
}