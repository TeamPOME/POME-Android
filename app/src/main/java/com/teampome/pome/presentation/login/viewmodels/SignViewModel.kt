package com.teampome.pome.presentation.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignViewModel : ViewModel() {
    private val userNickname = MutableLiveData<String>()
    private val isVaildNickname = MutableLiveData<Boolean>()
    private var isCompletedSignUp = MutableLiveData<Boolean>()


    fun signUp() {
        if (isVaildNickname.value == false) return
        else isCompletedSignUp.value = true
    }

    fun onNicknameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userNickname.value = s.toString().trim()
        checkNicknameFormat()
    }

    private fun checkNicknameFormat() {
        if (userNickname.value == null) return
        else isVaildNickname.value = true

    }

    fun getUserNickname(): LiveData<String> = userNickname
    fun getVaildNickname(): LiveData<Boolean?> = isVaildNickname
    fun getCompleteSignUp(): LiveData<Boolean> = isCompletedSignUp


}

// 특수문자 : (?=.*[-._])
//  val passwordPattern =
//  Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[가-힣]).{1,10}.$")
// isVaildNickname.value = passwordPattern.matcher(userNickname.value).matches()