package com.rickaban.android.getrandomusers.screen.random_user_list.model

data class RandomUser(
    val randomUserID: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val profilePictureUrl: String,
    val username: String,
    val email: String,
    val phone: String,
    val cell: String,
    val nationalityStringResourceID: Int,
    val birthday: String
)                        
