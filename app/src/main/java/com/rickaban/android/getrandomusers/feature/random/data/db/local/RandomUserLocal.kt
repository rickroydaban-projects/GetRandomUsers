package com.rickaban.android.getrandomusers.feature.random.data.db.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

@Entity(tableName = "random_user")
data class RandomUserLocal(
    @PrimaryKey val randomUserID: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val profilePictureUrl: String,
    //contact details
    val username: String,
    val email: String,
    val phone: String,
    val cell: String,
    //other
    val nationality: String,
    val birthday: String

){
    fun model(
        nationalityStringResourceID: Int
    ) = RandomUser(
        randomUserID = randomUserID,
        firstName = firstName,
        lastName = lastName,
        address = address,
        profilePictureUrl = profilePictureUrl,
        username = username,
        email = email,
        phone = phone,
        cell = cell,
        nationalityStringResourceID = nationalityStringResourceID,
        birthday = birthday
    )
}
