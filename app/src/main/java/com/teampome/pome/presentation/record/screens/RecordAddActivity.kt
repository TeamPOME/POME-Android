package com.teampome.pome.presentation.record.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityRecordAddBinding
import com.teampome.pome.presentation.main.MainActivity

class RecordAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordAddBinding.inflate(layoutInflater)
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