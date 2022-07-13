package com.teampome.pome.presentation.record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityGoalDateBinding
import com.teampome.pome.util.setVisibility
import timber.log.Timber

class GoalDateActivity : AppCompatActivity(), CalendarStartBottomSheet.OnClickListener, CalendarEndBottomSheet.OnClickListener {

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
        binding.btnEndcalendar.setOnClickListener {
            val bottomSheet = CalendarEndBottomSheet()
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

    override fun onReceiveStartData(name: String) {
        binding.tvGoalstartdate.text = name
        binding.tvChoicestartdate.setVisibility(false)
        Timber.d("date $name")
        CalendarEndBottomSheet().apply {
            arguments =
                Bundle().apply {
                    putString("date123", name)
                }
        }
    }

    override fun onReceiveEndData(name: String) {
        binding.tvGoalenddate.text = name
        binding.tvChoiceenddate.setVisibility(false)
    }
}