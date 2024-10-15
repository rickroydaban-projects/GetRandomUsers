package com.rickaban.android.getrandomusers.feature.random.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import com.rickaban.android.getrandomusers.feature.random.data.api.action.GetRandomUserList
import com.rickaban.android.getrandomusers.feature.random.data.api.action.GetRandomUserDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomApi {

    @GET(" ")
    suspend fun getRandomUserList(
        @Query("results") results: Int
    ): GetRandomUserList.Response

    @POST("user/detail")
    suspend fun getRandomUserDetail(
        @Body body: GetRandomUserDetail.Request
    ): GetRandomUserDetail.Response
}