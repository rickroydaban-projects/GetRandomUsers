package com.rickaban.android.getrandomusers.feature.random.domain

import com.rickaban.android.getrandomusers.app.domain.ResultError
import com.rickaban.android.getrandomusers.feature.random.data.api.RandomApi
import com.rickaban.android.getrandomusers.feature.random.data.db.RandomDao
import com.rickaban.android.getrandomusers.feature.random.domain.use_case.GetRandomUserList
import com.rickaban.android.getrandomusers.feature.random.domain.use_case.getFullNationality
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException

@RunWith(JUnit4::class)
class GetRandomUserListTest {

    private lateinit var randomApi: RandomApi
    private lateinit var randomDao: RandomDao

    @Before
    fun setUp() {
        randomApi = mockk()
        randomDao = mockk()
    }

    @Test
    fun `invoke should return Success when API and DAO work correctly`() =
        runBlocking {
            val results = 1
            val mockResponse = MockResponseData.randomUserResponse()
            val localizedRandomUserList = mockResponse.results.map { it.localize() }
            val randomUserList = localizedRandomUserList.map { it.model(it.nationality.getFullNationality()) }

            coEvery { randomApi.getRandomUserList(results) } returns mockResponse
            coEvery { randomDao.upsertRandomUserList(localizedRandomUserList) } just Runs

            val result = GetRandomUserList(
                results,
                randomApi,
                randomDao
            )

            assert(result is com.rickaban.android.getrandomusers.app.domain.Result.Success)
            assertEquals(
                randomUserList,
                (result as com.rickaban.android.getrandomusers.app.domain.Result.Success).data
            )
            coVerify(exactly = 1) { randomApi.getRandomUserList(results) }
            coVerify(exactly = 1) { randomDao.upsertRandomUserList(localizedRandomUserList) }
        }

    @Test
    fun `invoke should return Failure on HttpException`() =
        runBlocking {
            val results = 1
            val httpException = mockk<HttpException> {
                every { code() } returns 500
            }

            coEvery { randomApi.getRandomUserList(results) } throws httpException

            val result = GetRandomUserList(
                results,
                randomApi,
                randomDao
            )

            assert(result is com.rickaban.android.getrandomusers.app.domain.Result.Failure)
            assertEquals(
                ResultError.Connection,
                (result as com.rickaban.android.getrandomusers.app.domain.Result.Failure).error
            )
            coVerify(exactly = 1) { randomApi.getRandomUserList(results) }
            coVerify(exactly = 0) { randomDao.upsertRandomUserList(any()) }
        }

    @Test
    fun `invoke should return Failure on generic Exception`() =
        runBlocking {
            val results = 1
            coEvery { randomApi.getRandomUserList(results) } throws ArrayIndexOutOfBoundsException()

            val result = GetRandomUserList(
                results,
                randomApi,
                randomDao
            )

            assert(result is com.rickaban.android.getrandomusers.app.domain.Result.Failure)
            assertEquals(
                ResultError.Generic,
                (result as com.rickaban.android.getrandomusers.app.domain.Result.Failure).error
            )
            coVerify(exactly = 1) { randomApi.getRandomUserList(results) }
            coVerify(exactly = 0) { randomDao.upsertRandomUserList(any()) }
        }
}