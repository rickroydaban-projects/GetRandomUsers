package com.rickaban.android.getrandomusers.feature.random.data.api.action
       
import com.google.gson.annotations.SerializedName
import com.rickaban.android.getrandomusers.feature.random.data.db.local.RandomUserDetailLocal

class GetRandomUserDetail {
    data class Request(
        @SerializedName("random_user_id") val randomUserID: Long
    )

    data class Response(
        @SerializedName("random_user_detail") val randomUserDetail: RandomUserDetailRemote
    )

    data class RandomUserDetailRemote(
        @SerializedName("random_user_id") val randomUserID: Long,
        @SerializedName("name") val name: String
    ){
        fun localize() = RandomUserDetailLocal(
            randomUserID = randomUserID,
            name = name
        )
    }                            
}            
       