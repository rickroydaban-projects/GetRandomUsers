package com.rickaban.android.getrandomusers.screen.random_user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.rickaban.android.getrandomusers.app.domain.Result
import com.rickaban.android.getrandomusers.feature.random.domain.RandomRepository
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser

@HiltViewModel
class RandomUserDetailViewModel @Inject constructor(
    private val randomRepository: RandomRepository
): ViewModel() {
    private val _state = MutableStateFlow(RandomUserDetailState())
    val state = _state.asStateFlow()

    fun getRandomUserDetail() {
        viewModelScope.launch {
            randomRepository.getRandomUserDetail().let { result ->
                when(result) {
                    is Result.Success -> {
                        //if ever decided to get latest user detail
                    }
                    else -> {

                    }
                }
            }
        }
    }
}