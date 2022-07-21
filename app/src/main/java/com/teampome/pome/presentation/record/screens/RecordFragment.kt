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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.teampome.pome.R
import com.teampome.pome.data.GoalService
import com.teampome.pome.data.RecordsService
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.presentation.record.RecordAdapter
import com.teampome.pome.presentation.record.RecordData
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator
import com.teampome.pome.util.enqueueUtil
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        noDragSeekBar()
        initGoalChip()
        initGoalDetail()
        goGoalDateActivity()
        initGoalRecord()
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
            onSuccess = { it ->
                it.data?.forEach {
                    categoryMap[it.category] = it.id
                }
                configureCategory(categoryMap)
            },
            onError = {
                requireContext().showToast("불러오기에 실패했습니다.")
            }
        )
    }

    private fun configureCategory(category: MutableMap<String, Int>) {
        val categoryName = mutableListOf<String>()
        category.forEach { categoryName.add(it.key) }

        categoryName.forEachIndexed { i, text ->
            if (i == 0) binding.cgGoal.addView(createChip(text, true))
            else binding.cgGoal.addView(createChip(text))
        }
    }

    private fun createChip(text: String, isChecked: Boolean = false): Chip {
        return (layoutInflater.inflate(R.layout.layout_chip, binding.cgGoal, false) as Chip).apply {
            this.text = text
            this.isChecked = isChecked
            layoutParams = ChipGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun getSelectedCategory(): String {
        var selectedCategory = ""
        binding.cgGoal.forEach {
            if ((it as Chip).isChecked) selectedCategory = it.text.toString()
        }
        return selectedCategory
    }

    private fun initGoalDetail() {

        val goalId = 24
        service.initGoalDetail(goalId).enqueueUtil(
            onSuccess = {
                visibilityGone()
                visibilityTrue()
                binding.goaldetail = it.data
                binding.ivLock.isSelected = it.data?.isPublic ?: error("바인딩 에러")
                binding.tvSeekbar.x = (it.data.rate * 2.6 + 1).toFloat()
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