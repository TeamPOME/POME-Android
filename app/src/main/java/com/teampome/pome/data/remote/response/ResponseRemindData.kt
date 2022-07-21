package com.teampome.pome.data.remote.response

data class ResponseRemindData(
    val id:Int,
    val amount:Int,
    val content:String,
    val startEmotion:Int,
    val endEmotion:Int,
    val timestamp:String,
    val reactions:List<Int>,
    val plusCount:Int
)
