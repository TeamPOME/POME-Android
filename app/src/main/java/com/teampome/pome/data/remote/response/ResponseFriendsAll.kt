package com.teampome.pome.data.remote.response

data class ResponseFriendsAll(
    val id:String,
    var userId:Int,
    val nickname:String,
    val profileImage:String,
    val goalMessage:String,
    val amount:Int,
    val content:String,
    val startEmotion:Int,
    val endEmotion:Int,
    val timestamp:String,
    val reaction:List<Int>,
    val plusCount:Int
)
