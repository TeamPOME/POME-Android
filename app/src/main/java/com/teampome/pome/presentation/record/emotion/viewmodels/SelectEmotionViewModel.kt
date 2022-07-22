package com.teampome.pome.presentation.record.emotion.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectEmotionViewModel : ViewModel() {
    val one = MutableLiveData<Boolean>()
    val two = MutableLiveData<Boolean>()
    val three = MutableLiveData<Boolean>()
    private val isCompleted = MutableLiveData<Boolean>()
}