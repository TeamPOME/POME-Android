package com.teampome.pome.presentation.friends

data class FriendsProfileData(

    val data:friends
){
    data class friends(
        val name:String,
        val image:String
    )
}
