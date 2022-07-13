package com.teampome.pome.presentation.record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityGoalDateBinding

class GoalDateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoalDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        calendarClickEvent()
        goGoalDetailActivity()
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
    }

    private fun goGoalDetailActivity() {
        binding.btnChoice.setOnClickListener {
            //나중에 로직 짤 때 캘린더 다 채웠는지 검사
            val intent = Intent(this, GoalDetailActivity::class.java)
            startActivity(intent)
        }
    }
}