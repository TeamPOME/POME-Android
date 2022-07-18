package com.teampome.pome.presentation.record.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoalDetailViewModel : ViewModel() {
    var goalcategory = MutableLiveData<String>()
    var goalresolution = MutableLiveData<String>()
    var goalamount = MutableLiveData<String>()
    var isDetailSuccess = MutableLiveData(false)

    fun completeDetailCheck() {
        return if (!goalcategory.value.isNullOrEmpty() && !goalresolution.value.isNullOrEmpty() && !goalamount.value.isNullOrEmpty()) {
            isDetailSuccess.value = true
        } else {
            isDetailSuccess.value = false
        }
    }
}