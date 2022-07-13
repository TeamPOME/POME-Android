package com.teampome.pome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.teampome.pome.databinding.ActivityMainBinding
import com.teampome.pome.presentation.friends.FriendsFragment
import com.teampome.pome.presentation.record.RecordFragment
import com.teampome.pome.presentation.remind.RemindFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val recordFragment by lazy { RecordFragment() }
    private val friendsFragment by lazy { FriendsFragment() }
    private val remindFragment by lazy { RemindFragment() }
    private lateinit var binding : ActivityMainBinding

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