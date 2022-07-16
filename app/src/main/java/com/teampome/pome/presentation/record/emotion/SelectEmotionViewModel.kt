package com.teampome.pome.presentation.record.emotion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectEmotionViewModel : ViewModel() {
    val one = MutableLiveData<Boolean>()
    val two = MutableLiveData<Boolean>()
    val three = MutableLiveData<Boolean>()
    private val isCompleted = MutableLiveData<Boolean>()

}
//button true일 시 해당 textview -> 버튼이 true인가 ? @color/pomesub : @color/pome_grey7