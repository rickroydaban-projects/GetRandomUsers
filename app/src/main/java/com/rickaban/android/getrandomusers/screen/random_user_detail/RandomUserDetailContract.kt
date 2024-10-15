package com.rickaban.android.getrandomusers.screen.random_user_detail

import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

data class RandomUserDetailState(
    val showLoader: Boolean = false,
    val randomUser: RandomUser? = null
)

sealed interface RandomUserDetailAction {
    data object Dismiss: RandomUserDetailAction
}