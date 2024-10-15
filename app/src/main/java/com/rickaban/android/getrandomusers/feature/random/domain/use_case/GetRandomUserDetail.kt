package com.rickaban.android.getrandomusers.feature.random.domain.use_case

import com.rickaban.android.getrandomusers.app.domain.ResultError
import com.rickaban.android.getrandomusers.app.domain.Result
import com.rickaban.android.getrandomusers.app.domain.apiError
import com.rickaban.android.getrandomusers.feature.random.data.api.RandomApi
import com.rickaban.android.getrandomusers.feature.random.data.api.action.GetRandomUserDetail
import com.rickaban.android.getrandomusers.feature.random.data.db.RandomDao
import com.rickaban.android.getrandomusers.screen.random_user_detail.model.RandomUserDetail
import retrofit2.HttpException

object GetRandomUserDetail {
    suspend operator fun invoke(
        randomApi: RandomApi,
        randomDao: RandomDao
    ): Result<RandomUserDetail, ResultError> {
        return try {
            val localizedRandomUserDetail = randomApi.getRandomUserDetail(
                GetRandomUserDetail.Request(0)
            ).randomUserDetail.localize()

            val randomUserDetail = localizedRandomUserDetail.model()

            randomDao.upsertRandomUserDetail(localizedRandomUserDetail)
            Result.Success(randomUserDetail)
        } catch (e: HttpException) {
            Result.Failure(e.code().apiError())
        } catch (e: Exception) {
            Result.Failure(ResultError.Generic)
        }
    }
}