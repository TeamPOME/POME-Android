package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.teampome.pome.databinding.ActivityGoalDetailBinding
import com.teampome.pome.presentation.record.viewmodels.GoalDetailViewModel

class GoalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoalDetailBinding
    private val viewModel by viewModels<GoalDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goalDetailViewModel = viewModel
        binding.lifecycleOwner = this

        goBack()
        checkComplete()
        goGoalAddActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun checkComplete() {
        viewModel.goalcategory.observe(this) {
            viewModel.completeDetailCheck()
        }
        viewModel.goalresolution.observe(this) {
            viewModel.completeDetailCheck()
        }
        viewModel.goalamount.observe(this) {
            viewModel.completeDetailCheck()
        }

        viewModel.isDetailSuccess.observe(this) {
            binding.btnWrite.isSelected = it
        }
    }

    private fun goGoalAddActivity() {
        binding.btnWrite.setOnClickListener {
            if (binding.btnWrite.isSelected) {
                val intent = Intent(this, GoalAddActivity::class.java)
                startActivity(intent)
            }
        }
    }
}