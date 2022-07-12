package com.teampome.pome.presentation.record.emotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teampome.pome.databinding.ActivityAfterSelectEmotionBinding

class AfterSelectEmotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterSelectEmotionBinding

    //private val viewModel: SelectEmotionViewModel by viewModels()
    private var nowTypeNum: Int? = null
    private var prevTypeNum: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterSelectEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickIcon()
    }


    private fun clickIcon() {
        binding.btnHappy.setOnClickListener {
            binding.btnComplete.isClickable
            binding.btnHappy.isSelected = !binding.btnHappy.isSelected
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

}


/*
private fun initButtonClickListener() {
    binding.btnHappy.setOnClickListener(clickIcon(binding.btnHappy))
    binding.btnOh.setOnClickListener(clickIcon(binding.btnOh))
    binding.btnSad.setOnClickListener(clickIcon(binding.btnSad))
}

private fun clickIcon(view: View) = View.OnClickListener {
    when (view) {
        binding.btnHappy -> {
            binding.btnHappy.setImageResource(R.drawable.ic_click_emoji_happy)
        }
        binding.btnOh -> {
            binding.btnOh.setImageResource(R.drawable.ic_click_emoji_sad)
        }
        binding.btnSad -> {
            binding.btnSad.setImageResource(R.drawable.ic_click_emoji_sad)

        }
    }
}*/
