/*
package com.teampome.pome.presentation.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teampome.pome.data.repository.KaKaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class KaKaoViewModel @Inject constructor(
    private val kaKaoRepository: KaKaoRepository
) : ViewModel() {

    private var _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> get() = _accessToken

    fun login(social: String, socialToken: String) {
        viewModelScope.launch {
            runCatching {
                Timber.v("log.앞 카카오::[${social}] + 소셜토큰::[${socialToken}]")
                kaKaoRepository.login("kakao", socialToken)
            }.onSuccess {
                //TODO 쉐어드 프리퍼런스에 넣는 코드 or 함수 구현
                _accessToken.value = socialToken
                //Timber.v("[${it.data.accessToken}]")
            }.onFailure {
                Timber.e("viewmodel 실패")
            }
        }
    }
}
*/
