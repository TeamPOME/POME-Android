package com.teampome.pome.presentation.friends

data class FriendsConsumeData(
    val name:String,
    val date:String,
    val price:String, //나중에 int로 받는지 확인하기
    val description:String,
    val first_emotion:Int,
    val second_emotion:Int,
    val tag:String,
    val reaction:List<Int>
    //친구들 반응 리스트는 나중에
)
