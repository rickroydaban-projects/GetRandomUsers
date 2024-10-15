package com.rickaban.android.getrandomusers.screen.random_user_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.rickaban.android.getrandomusers.R
import com.rickaban.android.getrandomusers.destinations.SettingScreenDestination
import com.rickaban.android.getrandomusers.screen.random_user_detail.RandomUserDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
@RootNavGraph(start = true)
fun RandomUserListScreen(
    navigator: DestinationsNavigator
){
    val viewModel: RandomUserListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.showLoader)
    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(lazyListState.isScrollInProgress) {
        keyboardController?.hide()
    }

    AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
        RandomUserListSheet(
            state = state,
            swipeRefreshState = swipeRefreshState,
            lazyListState =  lazyListState,
            scrollBehavior = scrollBehavior,
            action = { action ->
                when(action) {
                    is RandomUserListAction.ClickBack -> navigator.navigateUp()
                    is RandomUserListAction.NavToSettings -> navigator.navigate(SettingScreenDestination)
                    is RandomUserListAction.UpdateQuery -> viewModel.updateQuery(action.query)
                    is RandomUserListAction.ClearQuery -> viewModel.updateQuery("")
                    is RandomUserListAction.DismissKeyboard -> keyboardController?.hide()
                    is RandomUserListAction.ClickRandomUser -> viewModel.setRandomUser(action.randomUser)
                    is RandomUserListAction.SwipeRefresh -> viewModel.getRandomUserList()
                }
            }
        )
    }

    state.detail?.let { randomUser ->
        RandomUserDetailScreen(
            randomUser = randomUser,
            onDismiss = {
                viewModel.setRandomUser(null)
            }
        )
    }
 }