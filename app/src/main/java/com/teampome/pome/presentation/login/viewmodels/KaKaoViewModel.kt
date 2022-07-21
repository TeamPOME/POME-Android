package com.teampome.pome.presentation.login.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teampome.pome.data.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/*@HiltViewModel
class KaKaoViewModel @Inject constructor(
     var authService: AuthService
) : ViewModel() {

    private var _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> get() = _accessToken

    fun login(social: String, socialToken: String) {
        viewModelScope.launch {
            runCatching {
               Log.e("viewmodel","runcatching")
                authService.login("kakao", socialToken)
            }.onSuccess {
                _accessToken.value = socialToken
            }.onFailure {
                Timber.e("viewmodel 실패")
            }
        }
    }
}*/

