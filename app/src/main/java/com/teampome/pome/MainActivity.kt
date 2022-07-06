package com.teampome.pome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.skydoves.balloon.*
import com.teampome.pome.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val iconBalloon by balloon<FriendPageIconFactory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.d("example")
        iconClickEvent()

    }

    private fun iconClickEvent() {
        binding.ivIcon.setOnClickListener {
            iconBalloon.showAlignBottom(it)
        }
        val button: ImageView =
            iconBalloon.getContentView().findViewById(R.id.iv_first)
        button.setOnClickListener {
            binding.ivIcon.setImageResource(R.drawable.ic_what_mint)
            iconBalloon.dismiss()
        }

    }
}
