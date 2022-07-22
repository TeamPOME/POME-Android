package com.teampome.pome.presentation.record.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GoalIdViewModel : ViewModel() {
    val goalId = MutableLiveData<Int>()
}