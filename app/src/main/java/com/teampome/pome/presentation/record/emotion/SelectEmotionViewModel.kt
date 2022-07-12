package com.teampome.pome.presentation.record.emotion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectEmotionViewModel : ViewModel() {

    private var isVaildCheckIcon = MutableLiveData<Boolean>()
    private var isCompletedIcon = MutableLiveData<Boolean>()
    var isOneClick = MutableLiveData<Boolean>()
    var isNoClick = MutableLiveData(false)


    // icon 클릭 후 남겼어요 버튼 활성화 위한 true
    fun completeIcon() {
        if (isVaildCheckIcon.value == false) return
        else isCompletedIcon.value = true
    }

    fun checkIcon() {
        if (isOneClick.value == false) return
    }

    fun getVaildIcon(): LiveData<Boolean?> = isVaildCheckIcon
    fun getCompleteIcon(): LiveData<Boolean> = isCompletedIcon
}
//button true일 시 해당 textview -> 버튼이 true인가 ? @color/pomesub : @color/pome_grey7