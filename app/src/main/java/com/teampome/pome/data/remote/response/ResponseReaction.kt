package com.teampome.pome.data.remote.response

data class ResponseReaction(
    val _id:String,
    val reaction_emoji:List<Int>,
    val reaction_name:List<String>
)
