package com.teampome.pome.data.remote.response

data class ResponseRemindData(
    val goalMessage:String,
    val isGoalPublic:Boolean,
    val amount:Int,
    val content:String,
    val startEmotion:Int,
    val endEmotion:Int,
    val timestamp:String
)
