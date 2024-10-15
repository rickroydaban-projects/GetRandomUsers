package com.rickaban.android.getrandomusers.screen.random_user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.rickaban.android.getrandomusers.app.domain.Result
import com.rickaban.android.getrandomusers.feature.random.domain.RandomRepository
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(FlowPreview::class)
@HiltViewModel
class RandomUserListViewModel @Inject constructor(
    private val randomRepository: RandomRepository
): ViewModel() {
    private val _state = MutableStateFlow(RandomUserListState())
    val state = _state.asStateFlow()
    private val _searchDebounce = MutableStateFlow("")
    private var searchJob: Job? = null

    init {
        _state.update {
            it.copy(
                showLoader = false
            )
        }

        viewModelScope.launch {
            _searchDebounce
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest {
                    if(it.isNotEmpty()) {
                        getRandomUserList()
                    }
                }
        }
    }

    fun updateQuery(query: String) {
        searchJob?.cancel()
        _state.update {
            it.copy(
                query = query,
                randomUserList = null,
                error = null
            )
        }

        _searchDebounce.value = query
    }

    fun getRandomUserList() {
        if(state.value.query.isNotEmpty() && state.value.query.toIntOrNull() != null) {
            _state.update {
                it.copy(showLoader = true)
            }

            searchJob = viewModelScope.launch {
                randomRepository.getRandomUserList(state.value.query.toInt()).let { result ->
                    when(result) {
                        is Result.Success -> {
                            _state.update {
                                it.copy(
                                    randomUserList = result.data,
                                    showLoader = false
                                )
                            }
                        }
                        is Result.Failure -> {
                            _state.update {
                                it.copy(
                                    error = result.error,
                                    showLoader = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun setRandomUser(
        randomUser: RandomUser?
    ) {
        _state.update {
            it.copy(detail = randomUser)
        }
    }
}