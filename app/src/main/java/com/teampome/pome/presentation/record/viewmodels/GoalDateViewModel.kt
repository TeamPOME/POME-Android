package com.teampome.pome.presentation.record.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoalDateViewModel : ViewModel() {
    var goalStartDate = MutableLiveData<String>()
    var goalEndDate = MutableLiveData<String>()

    var isDateSuccess = MutableLiveData(true)

    fun completeDateCheck() {
        return if (!goalStartDate.value.isNullOrEmpty() && !goalEndDate.value.isNullOrEmpty()) {
            isDateSuccess.value = true
        } else {
            isDateSuccess.value = false
        }
    }
}