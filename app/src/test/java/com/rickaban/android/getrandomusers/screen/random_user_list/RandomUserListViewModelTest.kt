package com.rickaban.android.getrandomusers.screen.random_user_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rickaban.android.getrandomusers.app.domain.ResultError
import com.rickaban.android.getrandomusers.feature.random.domain.RandomRepository
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

@ExperimentalCoroutinesApi
class RandomUserListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val randomRepository: RandomRepository = mockk()
    private lateinit var viewModel: RandomUserListViewModel

    private val mockRandomUser = RandomUser(
        randomUserID = "12345",
        firstName = "John",
        lastName = "Doe",
        address = "123 Main St, Anytown, USA",
        profilePictureUrl = "https://example.com/profile.jpg",
        username = "johndoe",
        email = "johndoe@example.com",
        phone = "+1-555-1234",
        cell = "+1-555-5678",
        nationalityStringResourceID = 1, // Example resource ID
        birthday = "1990-01-01"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = RandomUserListViewModel(randomRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `test updateQuery triggers getRandomUserList with valid input`() = runTest {
        val query = "1"

        coEvery { randomRepository.getRandomUserList(1) } returns com.rickaban.android.getrandomusers.app.domain.Result.Success(listOf(mockRandomUser))

        viewModel.updateQuery(query)
        advanceTimeBy(400) // Advance time to trigger debounce

        assertEquals(query, viewModel.state.value.query)
        assertFalse(viewModel.state.value.showLoader)
        coVerify { randomRepository.getRandomUserList(1) }
    }

    @Test
    fun `test updateQuery with invalid input does not trigger getRandomUserList`() = runTest {
        val query = "abc" // Invalid input
        viewModel.updateQuery(query)
        advanceTimeBy(400) // Advance time to trigger debounce

        viewModel.getRandomUserList()

        assertEquals(query, viewModel.state.value.query)
        assertTrue(!viewModel.state.value.showLoader)
        coVerify(exactly = 0) { randomRepository.getRandomUserList(any()) }
    }

    @Test
    fun `test getRandomUserList returns success`() = runTest {
        val query = "1"
        coEvery { randomRepository.getRandomUserList(1) } returns com.rickaban.android.getrandomusers.app.domain.Result.Success(listOf(mockRandomUser))

        viewModel.updateQuery(query)
        advanceTimeBy(400) // Advance time to trigger debounce

        assertTrue(viewModel.state.value.randomUserList != null)
        assertTrue(!viewModel.state.value.showLoader)
    }

    @Test
    fun `test getRandomUserList returns failure`() = runTest {
        val query = "1"
        coEvery { randomRepository.getRandomUserList(1) } returns com.rickaban.android.getrandomusers.app.domain.Result.Failure(ResultError.Connection)

        viewModel.updateQuery(query)
        advanceTimeBy(400) // Advance time to trigger debounce

        assertTrue(viewModel.state.value.error != null)
        assertTrue(!viewModel.state.value.showLoader)
    }

    @Test
    fun `test setRandomUser updates state correctly`() {
        val randomUser = mockRandomUser

        viewModel.setRandomUser(randomUser)

        assertEquals(randomUser, viewModel.state.value.detail)
    }
}
