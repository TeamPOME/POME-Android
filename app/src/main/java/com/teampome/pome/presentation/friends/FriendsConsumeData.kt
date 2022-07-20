package com.teampome.pome.presentation.friends

data class FriendsConsumeData(
    val id:String,
    var userId:Int,
    val name:String,
    val date:String,
    val price:Int,
    val description:String,
    val first_emotion:Int,
    val second_emotion:Int,
    val tag:String,
    var reaction:List<Int>,
    val plusCount:Int,
    val profileImage:String
    //친구들 반응 리스트는 나중에
)
