package com.teampome.pome.data.remote.response

data class ResponseFriendsAll(
    val _id:String,
    val name:String,
    val first_emotion:Int,
    val second_emotion:Int,
    val tag:String,
    val date:String,
    val description:String,
    val profile_image:String,
    val addEmoji:Boolean,
    val reaction_list:List<Int>?
)
