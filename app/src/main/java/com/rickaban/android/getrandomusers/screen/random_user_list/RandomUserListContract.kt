package com.rickaban.android.getrandomusers.screen.random_user_list

import com.rickaban.android.getrandomusers.app.domain.ResultError
import com.rickaban.android.getrandomusers.screen.random_user_detail.model.RandomUserDetail
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

data class RandomUserListState(
    val query: String = "",
    val showLoader: Boolean = false,
    val randomUserList: List<RandomUser>? = null,
    val detail: RandomUser? = null,
    val error: ResultError? = null
)

sealed interface RandomUserListAction {
    data object ClickBack: RandomUserListAction
    data object NavToSettings: RandomUserListAction
    data class UpdateQuery(val query: String): RandomUserListAction
    data object ClearQuery: RandomUserListAction
    data object DismissKeyboard: RandomUserListAction
    data class ClickRandomUser(val randomUser: RandomUser): RandomUserListAction
    data object SwipeRefresh: RandomUserListAction
    
}