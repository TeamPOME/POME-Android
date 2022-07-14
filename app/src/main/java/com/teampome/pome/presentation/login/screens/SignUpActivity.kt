package com.teampome.pome.presentation.login.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import coil.load
import com.teampome.pome.R
import com.teampome.pome.databinding.ActivitySignUpBinding
import com.teampome.pome.presentation.friends.screens.FriendsBottomSheetFragment
import com.teampome.pome.presentation.login.viewmodels.SignViewModel
import timber.log.Timber

class SignUpActivity : AppCompatActivity(), SignUpBottomSheetFragment.OnListenerButton {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignViewModel by viewModels()
    private val signUpBottomSheetFragment = SignUpBottomSheetFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@SignUpActivity
        goToAddFriendActivity()
        btnClickEvent()
    }

    private fun goToAddFriendActivity() {
        viewModel.getCompleteSignUp().observe(this) { isCompleted ->
            if (!isCompleted) return@observe
            Intent(this, SignUpContentActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }
    }

    private fun btnClickEvent() {
        binding.ivPlus.setOnClickListener {
            goGallery()
            //showBottomSheet()
        }
        binding.btnDelete.setOnClickListener {
            binding.etNickname.text.clear()
        }
    }

    private val activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                var currentImageUri = result.data?.data
                binding.ivProfile.load(currentImageUri)
            } else if (result.resultCode == RESULT_CANCELED) {
                Timber.d("사진선택실패")
            } else {
                Timber.d("사진첨부실패")
            }
        }

    private fun goGallery() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activityLauncher.launch(intent)
    }

    // bottomsheet 관련 로직
    private fun showBottomSheet() {
        signUpBottomSheetFragment.show(
            supportFragmentManager, signUpBottomSheetFragment.tag
        )
    }

    override fun onCheckedState(state: Int) {
        if (state == 0) goGallery()
        else binding.ivProfile.setImageResource(R.drawable.ic_profile_empty)
        signUpBottomSheetFragment.dismiss()
    }
}