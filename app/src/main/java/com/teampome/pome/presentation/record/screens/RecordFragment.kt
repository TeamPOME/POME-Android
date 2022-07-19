package com.teampome.pome.presentation.record.screens

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.teampome.pome.R
import com.teampome.pome.data.GoalService
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.presentation.record.RecordAdapter
import com.teampome.pome.presentation.record.RecordData
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.showToast
import javax.inject.Inject

class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    @Inject
    lateinit var service: GoalService
    private lateinit var recordAdapter: RecordAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goGoalDateActivity()
        initAdapter()
        noGoalClickEvent()
        goLookBackActivity()
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
                    if (index == 0){
                        val chip = Chip(context).apply {
                            id = responseGoalCreate.id
                            text = responseGoalCreate.category
                            setTextAppearanceResource(R.style.PomeSb14)
                            isCheckable = true
                            isCheckedIconVisible = false
                            chipBackgroundColor = ColorStateList(
                                arrayOf(
                                    intArrayOf(-android.R.attr.state_checked),
                                    intArrayOf(android.R.attr.state_checked)
                                ),
                                intArrayOf(Color.WHITE, ContextCompat.getColor(context, R.color.pome_main))
                            )
                            setTextColor(
                                ColorStateList(
                                    arrayOf(
                                        intArrayOf(-android.R.attr.state_checked),
                                        intArrayOf(android.R.attr.state_checked)
                                    ),
                                    intArrayOf(ContextCompat.getColor(context, R.color.pome_grey_5), Color.WHITE)
                                )
                            )
                        }
                        binding.cgGoal.addView(chip)
                        chip.isChecked = true
                    } else {
                        val chip = Chip(context).apply {
                            id = responseGoalCreate.id
                            text = responseGoalCreate.category
                            setTextAppearanceResource(R.style.PomeSb14)
                            isCheckable = true
                            isCheckedIconVisible = false
                            chipBackgroundColor = ColorStateList(
                                arrayOf(
                                    intArrayOf(-android.R.attr.state_checked),
                                    intArrayOf(android.R.attr.state_checked)
                                ),
                                intArrayOf(Color.WHITE, ContextCompat.getColor(context, R.color.pome_main))
                            )
                            setTextColor(
                                ColorStateList(
                                    arrayOf(
                                        intArrayOf(-android.R.attr.state_checked),
                                        intArrayOf(android.R.attr.state_checked)
                                    ),
                                    intArrayOf(ContextCompat.getColor(context, R.color.pome_grey_5), Color.WHITE)
                                )
                            )
                        }
                        binding.cgGoal.addView(chip)
                        chip.isChecked = false
                    }
                    binding.cgGoal.isSingleSelection = true
                }
            },
            onError = {
                requireContext().showToast("불러오기에 실패했습니다.")
            }
        )
    }

    private fun initAdapter() {
        recordAdapter = RecordAdapter()
        binding.rvRecord.adapter = recordAdapter
        addList()
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

    private fun addList() {
        recordAdapter.submitList(
            listOf(
                RecordData(1, "06.24", 1, "10,000원", "gg"),
                RecordData(1, "06.25", 1, "100,000원", "gg"),
                RecordData(1, "06.26", 1, "200,000원", "gg"),
                RecordData(1, "06.27", 1, "300,000원", "gg"),
                RecordData(1, "06.28", 1, "500,000원", "gg"),
                RecordData(1, "06.29", 1, "1,000,000원", "gg")
            )
        )
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
}