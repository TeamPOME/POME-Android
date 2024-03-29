package com.teampome.pome.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivityMainBinding
import com.teampome.pome.presentation.friends.screens.FriendsFragment
import com.teampome.pome.presentation.mypage.MyPageFragment
import com.teampome.pome.presentation.record.screens.RecordFragment
import com.teampome.pome.presentation.remind.screens.RemindFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val recordFragment by lazy { RecordFragment() }
    private val friendsFragment by lazy { FriendsFragment() }
    private val remindFragment by lazy { RemindFragment() }
    private val mypageFragment by lazy { MyPageFragment() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavi()
    }

    private fun initBottomNavi() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_record -> {
                    changeFragment(recordFragment)
                }
                R.id.menu_friends -> {
                    changeFragment(friendsFragment)
                }
                R.id.menu_remind -> {
                    changeFragment(remindFragment)
                }
                R.id.menu_mypage -> {
                    changeFragment(mypageFragment)
                }
            }
            true
        }
        binding.bnvMain.selectedItemId = R.id.menu_record
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.cl_main, fragment)
            .commit()
    }
}
