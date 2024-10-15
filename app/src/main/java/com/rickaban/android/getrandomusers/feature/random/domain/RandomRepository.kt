package com.rickaban.android.getrandomusers.feature.random.domain

import com.rickaban.android.getrandomusers.app.data.api.ApiManager.Companion.randomApi
import com.rickaban.android.getrandomusers.app.data.db.DBManager.Companion.randomDao
import com.rickaban.android.getrandomusers.app.domain.ResultError
import com.rickaban.android.getrandomusers.app.domain.Result
import com.rickaban.android.getrandomusers.feature.random.domain.use_case.GetRandomUserDetail
import com.rickaban.android.getrandomusers.feature.random.domain.use_case.GetRandomUserList
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser
import com.rickaban.android.getrandomusers.screen.random_user_detail.model.RandomUserDetail

interface RandomRepository {

    suspend fun getRandomUserList(results: Int): Result<List<RandomUser>, ResultError>
    suspend fun getRandomUserDetail(): Result<RandomUserDetail, ResultError>
}

class AppRandomRepository: RandomRepository {

    override suspend fun getRandomUserList(results: Int) = GetRandomUserList(results, randomApi, randomDao)
    override suspend fun getRandomUserDetail() = GetRandomUserDetail(randomApi, randomDao)
}