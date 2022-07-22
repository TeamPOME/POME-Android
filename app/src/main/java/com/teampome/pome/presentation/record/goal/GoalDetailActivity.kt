package com.teampome.pome.presentation.record.goal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.teampome.pome.data.GoalService
import com.teampome.pome.data.remote.request.RequestGoalCreate
import com.teampome.pome.databinding.ActivityGoalDetailBinding
import com.teampome.pome.presentation.record.viewmodels.GoalDetailViewModel
import com.teampome.pome.util.enqueueUtil
import com.teampome.pome.util.setOnSingleClickListener
import com.teampome.pome.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GoalDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var service: GoalService
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
        binding.btnBack.setOnSingleClickListener {
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
        binding.btnWrite.setOnSingleClickListener {
            if (binding.btnWrite.isSelected) {
                goalCreateNetwork()
            }
        }
    }

    private fun goalCreateNetwork() {
        val intent = intent
        val startDate = intent.getStringExtra("startDate")?.replace(".", "-")
        val endDate = intent.getStringExtra("endDate")?.replace(".", "-")

        val requestGoalCreate = RequestGoalCreate(
            startDate = startDate.toString(),
            endDate = endDate.toString(),
            category = binding.etGoalcategory.text.toString(),
            message = binding.etResolution.text.toString(),
            amount = binding.etGoalamount.text.toString().toInt(),
            isPublic = binding.swLock.isChecked
        )

        service.createGoal(requestGoalCreate).enqueueUtil(
            onSuccess = {
                startActivity(Intent(this@GoalDetailActivity, GoalAddActivity::class.java))
                if(!isFinishing) finish()
            },
            onError = {
                when(it) {
                    400 -> showToast("잘못된 요청 값입니다.")
                    401 -> showToast("인증 되지 않은 요청입니다.")
                    500 -> showToast("서버 내부 오류입니다.")
                }
            }
        )
    }
}