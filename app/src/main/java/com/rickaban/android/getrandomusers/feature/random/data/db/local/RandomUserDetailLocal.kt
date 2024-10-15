package com.rickaban.android.getrandomusers.feature.random.data.db.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rickaban.android.getrandomusers.screen.random_user_detail.model.RandomUserDetail

@Entity(tableName = "random_user_detail")
data class RandomUserDetailLocal(
    @PrimaryKey val randomUserID: Long,
    val name: String
){
    fun model() = RandomUserDetail(
        randomUserID = randomUserID,
        name = name
    )
}

fun RandomUserDetail.localize() = RandomUserDetailLocal(
    randomUserID = randomUserID,
    name = name
)
       