package com.rickaban.android.getrandomusers.screens.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.rickaban.android.getrandomusers.app.ui.AppActivity
import com.rickaban.android.getrandomusers.app.ui.AppViewModel

@Composable
@Destination
fun SettingScreen(
    navigator: DestinationsNavigator
) {
    val viewModel = hiltViewModel<AppViewModel>(viewModelStoreOwner = LocalContext.current as AppActivity)
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsSheet(
        state = state,
        action = { action ->
            when (action) {
                is SettingAction.NavBack -> navigator.navigateUp()
                is SettingAction.UpdateTheme -> {
                    viewModel.updateTheme(action.theme)
                }

                is SettingAction.UpdateNetworkEnabled -> {
                    viewModel.updateNetworkEnabled(action.enabled)
                }
            }
        }
    )
}        