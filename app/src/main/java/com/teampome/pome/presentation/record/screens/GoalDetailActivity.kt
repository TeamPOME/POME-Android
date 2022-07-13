package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityGoalDetailBinding

class GoalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
        goGoalAddActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun goGoalAddActivity() {
        binding.btnWrite.setOnClickListener {
            //나중에 로직 짤 때 editText 다 채웠는지 검사
            val intent = Intent(this, GoalAddActivity::class.java)
            startActivity(intent)
        }
    }
}