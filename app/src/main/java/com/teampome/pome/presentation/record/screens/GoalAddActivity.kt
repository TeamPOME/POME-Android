package com.teampome.pome.presentation.record.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.teampome.pome.MainActivity
import com.teampome.pome.databinding.ActivityGoalAddBinding
import com.teampome.pome.presentation.main.MainActivity

class GoalAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoalAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goRecordFragment()
    }

    private fun goRecordFragment() {
        binding.btnCheck.setOnClickListener {
            //여기서 정보 다 보내고 프래그먼트 이동하고 액티비티 종료
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAffinity(this)
        }
    }
}