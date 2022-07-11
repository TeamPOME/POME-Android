package com.teampome.pome.presentation.friends

interface FriendsProfileInterface
data class FriendsProfileData(
    val name: String,
    val profile_image: String,
) : FriendsProfileInterface

data class FriendsProfileWholeData(
    val name: String,
    val whole_image: String
) : FriendsProfileInterface
