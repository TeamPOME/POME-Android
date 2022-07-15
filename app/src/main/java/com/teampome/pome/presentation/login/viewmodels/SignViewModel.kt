package com.teampome.pome.presentation.login.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teampome.pome.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val userNickname = MutableStateFlow("")

    val isInputCheck = userNickname.map {
        it.isNotBlank()
    }

    fun getUser() {
        viewModelScope.launch {
            runCatching {
                repository.getUser()
            }.onSuccess {
                Timber.d("asdf", it.toString())
            }
        }
    }

}

// 특수문자 : (?=.*[-._])
//  val passwordPattern =
//  Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[가-힣]).{1,10}.$")
// isVaildNickname.value = passwordPattern.matcher(userNickname.value).matches()