package com.teampome.pome.presentation.record.screens

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.teampome.pome.R
import com.teampome.pome.data.GoalService
import com.teampome.pome.data.RecordsService
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.presentation.record.adapters.RecordAdapter
import com.teampome.pome.presentation.record.goal.*
import com.teampome.pome.presentation.record.viewmodels.GoalIdViewModel
import com.teampome.pome.util.decorate.CustomItemDecorator
import com.teampome.pome.util.decorate.VerticalItemDecorator
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RecordFragment : Fragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var service: GoalService

    @Inject
    lateinit var recordService: RecordsService
    private lateinit var recordAdapter: RecordAdapter
    private val viewModel by activityViewModels<GoalIdViewModel>()
    private var clickedChipPos: Int = -1
    private var clickedChipId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        deleteGoalNetwork()
        noDragSeekBar()
        initGoalChip()
        goGoalDateActivity()
        initAdapter()
        noGoalClickEvent()
    }

    private fun deleteGoalNetwork() {
        viewModel.goalId.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                runCatching {
                    service.deleteGoal(it)
                }.onSuccess {
                    binding.cgGoal.removeAllViews()
                    initGoalChip()
                }.onFailure {
                    Timber.d("$it")
                }
            }
        }
    }

    private fun goGoalDateActivity() {
        binding.apply {
            btnGoaladd.setOnSingleClickListener {
                if (cgGoal.size >= 11) {
                    showDialog()
                } else {
                    val intent = Intent(requireContext(), GoalDateActivity::class.java)
                    startActivity(intent)
                }
            }
            btnMakegoal.setOnSingleClickListener {
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
        lifecycleScope.launch {
            runCatching {
                service.initGoalChip()
            }.onSuccess {
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
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initGoalDetail(goalId: Int) {
        lifecycleScope.launch {
            runCatching {
                service.initGoalDetail(goalId)
            }.onSuccess {
                visibilityGoalTrue()
                binding.goaldetail = it.data
                binding.ivLock.isSelected = it.data?.isPublic ?: error("바인딩 에러")
                binding.tvSeekbar.visibility = View.GONE
                when (it.data.rate) {

                    999 -> {
                        val over = 290
                        binding.tvSeekbar.x = changeToDp(over) * 1.1.toFloat()
                        binding.sbGoal.progressDrawable = resources.getDrawable(
                            R.drawable.seekbar_custom_red,
                            resources.newTheme()
                        )
                        binding.sbGoal.thumb = resources.getDrawable(
                            R.drawable.seekbar_thumb_custom_red,
                            resources.newTheme()
                        )
                    }
                    0 -> {
                        val start = changeToDp(20)
                        binding.tvSeekbar.x = start.toFloat()
                        binding.sbGoal.progressDrawable = resources.getDrawable(
                            R.drawable.seekbar_custom_green,
                            resources.newTheme()
                        )
                        binding.sbGoal.thumb = resources.getDrawable(
                            R.drawable.seekbar_thumb_custom_green,
                            resources.newTheme()
                        )
                    }
                    else -> {
                        if (it.data.rate >= 70) {
                            //binding.tvSeekbar.x = changeToDp(it.data.rate).toFloat()
                            binding.sbGoal.progressDrawable = resources.getDrawable(
                                R.drawable.seekbar_custom_pink,
                                resources.newTheme()
                            )
                            binding.sbGoal.thumb = resources.getDrawable(
                                R.drawable.seekbar_thumb_custom_pink,
                                resources.newTheme()
                            )
                        } else { // 0-70
                            // binding.tvSeekbar.x=changeToDp(it.data.rate).toFloat()
                            binding.sbGoal.progressDrawable = resources.getDrawable(
                                R.drawable.seekbar_custom_green,
                                resources.newTheme()
                            )
                            binding.sbGoal.thumb = resources.getDrawable(
                                R.drawable.seekbar_thumb_custom_green,
                                resources.newTheme()
                            )
                        }
                        // binding.tvSeekbar.x = (it.data.rate * 2.8).toFloat()
                        binding.tvSeekbar.x = changeToDp(it.data.rate) * 3.3.toFloat()
                    }
                }
                val id = it.data.id
                binding.btnMore.setOnSingleClickListener {
                    val bottomSheet = GoalDeleteBottomSheet(id)
                    bottomSheet.show(childFragmentManager, bottomSheet.tag)
                }
                initGoalRecord(goalId)
            }.onFailure {
                Timber.d("$it")
            }
        }
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


    private fun changeToDp(value: Int): Int {
        val displayMetrics = requireContext().resources.displayMetrics
        val dp = Math.round(value * displayMetrics.density)
        return dp
    }

    private fun noGoalClickEvent() {
        binding.fabWrite.setOnSingleClickListener {
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
        lifecycleScope.launch {
            runCatching {
                recordService.initGoalRecord(goalId)
            }.onSuccess {
                if (it.data?.records?.size != 0) {
                    visibilityRecordTrue()
                    recordAdapter.submitList(it.data?.records)
                    binding.records = it.data
                    it.data?.incompleteTotal?.let { it1 -> goLookBackActivity(it1, goalId) }
                } else {
                    visibilityRecordFalse()
                }
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun goLookBackActivity(incompleteTotal: Int, goalId: Int) {
        binding.clLookback.setOnSingleClickListener {
            if (incompleteTotal > 0) {
                val intent = Intent(requireContext(), RecordLookBackActivity::class.java).apply {
                    putExtra("goalId", goalId)
                }
                startActivity(intent)
            } else {
                val dialog = NoEmotionDialogFragment()
                activity?.let { it1 ->
                    dialog.show(
                        it1.supportFragmentManager,
                        "NoEmotionDialogFragment"
                    )
                }
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}