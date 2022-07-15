package com.teampome.pome.presentation.record.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel: ViewModel() {
    val startDate = MutableLiveData<Date>()
    val endDate = MutableLiveData<Date>()

}