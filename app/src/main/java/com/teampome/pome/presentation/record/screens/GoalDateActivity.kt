package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.teampome.pome.databinding.ActivityGoalDateBinding
import com.teampome.pome.util.setVisibility
import timber.log.Timber
import java.text.SimpleDateFormat

class GoalDateActivity : AppCompatActivity() {

    private val viewModel by viewModels<CalendarViewModel>()
    private lateinit var binding: ActivityGoalDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        calendarClickEvent()
        goGoalDetailActivity()
        observes()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun calendarClickEvent() {
        binding.btnStartcalendar.setOnClickListener {
            val bottomSheet = CalendarStartBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
        binding.btnEndcalendar.setOnClickListener {
            val bottomSheet = CalendarEndBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
    }

    private fun observes() {
        viewModel.startDate.observe(this) {
            binding.tvGoalstartdate.text = SimpleDateFormat("yyyy.MM.dd").format(it.time)
            binding.tvChoicestartdate.setVisibility(false)
        }
        viewModel.endDate.observe(this) {
            binding.tvGoalenddate.text = SimpleDateFormat("yyyy.MM.dd").format(it.time)
            binding.tvChoiceenddate.setVisibility(false)
        }
    }

    private fun goGoalDetailActivity() {
//        binding.btnChoice.isSelected = binding.tvGoalstartdate.length() != 0 && binding.tvGoalenddate.length() != 0
        binding.btnChoice.setOnClickListener {
            if(binding.btnChoice.isSelected) {
                val intent = Intent(this, GoalDetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}