package com.teampome.pome.presentation.record.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecordWriteViewModel : ViewModel() {
    val goalchoice = MutableLiveData<String>()
    val consumedate = MutableLiveData<String>()
    val consumeamount = MutableLiveData<String>()
    val consumerecord = MutableLiveData<String>()

    val isWriteSuccess = MutableLiveData(false)

    fun completeWriteCheck() {
        return if (!consumeamount.value.isNullOrEmpty() && !consumerecord.value.isNullOrEmpty()) {
            isWriteSuccess.value = true
        } else {
            isWriteSuccess.value = false
        }
    }
}