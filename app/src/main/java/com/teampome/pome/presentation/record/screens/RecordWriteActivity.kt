package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.teampome.pome.databinding.ActivityRecordWriteBinding
import com.teampome.pome.databinding.FragmentGoalListBottomSheetBinding
import com.teampome.pome.presentation.record.emotion.BeforeSelectEmotionActivity
import com.teampome.pome.presentation.record.viewmodels.RecordWriteViewModel
import com.teampome.pome.util.setVisibility
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RecordWriteActivity : AppCompatActivity(), GoalListBottomSheet.OnListenerGoal, RecordCalendarBottomSheet.OnListenerDate {

    private lateinit var binding: ActivityRecordWriteBinding
    private val viewModel by viewModels<RecordWriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordWriteViewModel = viewModel
        binding.lifecycleOwner = this

        goBack()
        todaySetting()
        goalClickEvent()
        calendarClickEvent()
        checkComplete()
        goBeforeSelectEmotionActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun todaySetting() {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        val today : String = sdf.format(date)
        binding.tvDate.text = today
    }

    private fun goalClickEvent() {
        binding.btnDown.setOnClickListener {
            val bottomSheet = GoalListBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }

    private fun calendarClickEvent() {
        binding.btnCalendar.setOnClickListener {
            val bottomSheet = RecordCalendarBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }

    private fun checkComplete() {
        viewModel.goalchoice.observe(this) {
            viewModel.completeWriteCheck()
        }
//        viewModel.consumedate.observe(this) {
//            viewModel.completeWriteCheck()
//        }
        viewModel.consumeamount.observe(this) {
            viewModel.completeWriteCheck()
        }
        viewModel.consumerecord.observe(this) {
            viewModel.completeWriteCheck()
        }

        viewModel.isWriteSuccess.observe(this) {
            binding.btnWrite.isSelected = it
        }
    }

    private fun goBeforeSelectEmotionActivity() {
        binding.btnWrite.setOnClickListener {
            if (binding.btnWrite.isSelected) {
                val intent = Intent(this, BeforeSelectEmotionActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCheckedGoal(goal: String) {
        binding.apply {
            tvChoicegoal.text = goal
            tvGoal.setVisibility(false)
        }
    }

    override fun onReceiveDate(date: Date) {
        val choiceDate = SimpleDateFormat("yyyy.MM.dd").format(date)
        binding.apply {
            tvChoicedate.text = choiceDate
            tvDate.setVisibility(false)
        }
    }

}