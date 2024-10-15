package com.rickaban.android.getrandomusers.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rickaban.android.getrandomusers.BuildConfig
import com.rickaban.android.getrandomusers.R
import com.rickaban.android.getrandomusers.app.ui.AlignedRow
import com.rickaban.android.getrandomusers.app.ui.AppState
import com.rickaban.android.getrandomusers.app.ui.DropDown
import com.rickaban.android.getrandomusers.app.ui.theme.Theme
import com.rickaban.android.getrandomusers.app.ui.theme.ThemeWrapper
import com.rickaban.android.getrandomusers.app.ui.theme.theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSheet(
    state: AppState,
    action: (SettingAction) -> Unit
) {
    ThemeWrapper {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { action(SettingAction.NavBack) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    title = {
                        Text(text = stringResource(id = R.string.setting))
                    }
                )
            }
        ){ paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                ThemeItem(
                    theme = state.theme,
                    updateTheme = { theme ->
                        action(SettingAction.UpdateTheme(theme))
                    }
                )

                NetworkItem(
                    isNetworkEnabled = state.isNetworkEnabled,
                    updateNetworkEnabled = { networkEnabled ->
                        action(SettingAction.UpdateNetworkEnabled(networkEnabled))
                    }
                )

                AppVersion()

                Spacer(modifier = Modifier.width(50.dp))
            }
        }
    }
}

@Composable
private fun ThemeItem(
    theme: Theme,
    updateTheme: (Theme) -> Unit
){
    val expandedState = rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expandedState.value = true
            }
            .padding(
                horizontal = 17.dp,
                vertical = 12.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.theme),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(5.dp))
        DropDown(
            modifier = Modifier.weight(1f),
            selection = theme.value.toString(),
            onSelected = {
                updateTheme(it.toInt().theme())
            },
            expandedState = expandedState,
            selections = mapOf(
                Theme.SYSTEM.value.toString() to stringResource(id = R.string.system),
                Theme.LIGHT.value.toString() to stringResource(id = R.string.light),
                Theme.DARK.value.toString() to stringResource(id = R.string.dark)
            )
        )
    }
}

@Composable
private fun NetworkItem(
    isNetworkEnabled: Boolean,
    updateNetworkEnabled: (Boolean) -> Unit
){
    AlignedRow(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

            }
            .padding(horizontal = 17.dp)
    ) {
        Text(
            text = stringResource(id = R.string.network),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1F)
        )
        Switch(
            checked = isNetworkEnabled,
            onCheckedChange = {
                updateNetworkEnabled(it)
            }
        )
    }
}

@Composable
private fun AppVersion(){
    AlignedRow(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

            }
            .padding(
                horizontal = 17.dp,
                vertical = 12.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.app_version),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        Text(text = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
    }
}        