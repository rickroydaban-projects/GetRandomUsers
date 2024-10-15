package com.rickaban.android.getrandomusers.screen.random_user_detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.rickaban.android.getrandomusers.screen.random_user_list.model.RandomUser
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomUserDetailScreen(
    randomUser: RandomUser,
    onDismiss: ()->Unit
){
    val viewModel: RandomUserDetailViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() }
    ) {
        RandomUserDetailSheet(
            randomUser = randomUser,
            state = state,
            action = { action ->
                when(action) {
                    is RandomUserDetailAction.Dismiss -> coroutineScope.launch {
                        sheetState.hide()
                        onDismiss()
                    }
                }
            }
        )
    }
 }