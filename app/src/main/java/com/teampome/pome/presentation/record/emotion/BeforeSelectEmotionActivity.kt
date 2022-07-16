package com.teampome.pome.presentation.record.emotion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityBeforeSelectEmotionBinding
import com.teampome.pome.presentation.record.screens.RecordAddActivity

class BeforeSelectEmotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeforeSelectEmotionBinding

    //private val viewModel: SelectEmotionViewModel by viewModels()
    private var nowTypeNum: Int? = null
    private var prevTypeNum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeforeSelectEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goBack()
        iconlist()
        //clickIcon()
        goRecordAddActivity()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun clickIcon() {

        binding.btnHappy.setOnClickListener {
            binding.btnComplete.isClickable
            binding.btnHappy.isSelected = !binding.btnHappy.isSelected
            if(binding.btnHappy.isSelected) binding.tvHappy.setTextColor(ContextCompat.getColor(this@BeforeSelectEmotionActivity, R.color.pome_main))
            else binding.tvHappy.setTextColor(ContextCompat.getColor(this@BeforeSelectEmotionActivity, R.color.pome_grey_7))
        }
        binding.btnOh.setOnClickListener {
            binding.btnComplete.isClickable
            binding.btnOh.isSelected = !binding.btnOh.isSelected
        }
        binding.btnSad.setOnClickListener {
            binding.btnComplete.isClickable
            binding.btnSad.isSelected = !binding.btnSad.isSelected
        }
    }
    private fun iconlist() {
        val iconlist = listOf(
            binding.btnHappy,
            binding.btnOh,
            binding.btnSad
        )

        for (i in iconlist.indices)
            iconlist[i].setOnClickListener { isSelected ->
                nowTypeNum = i

                if (prevTypeNum != null && nowTypeNum != null)
                    iconlist[prevTypeNum!!].isSelected = false
                prevTypeNum = nowTypeNum
            }
    }


    private fun goRecordAddActivity() {
        binding.btnComplete.setOnClickListener {
            val intent = Intent(this, RecordAddActivity::class.java)
            startActivity(intent)
        }
    }
}