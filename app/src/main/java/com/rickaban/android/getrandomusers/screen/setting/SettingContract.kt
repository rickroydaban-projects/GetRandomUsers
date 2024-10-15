package com.rickaban.android.getrandomusers.screens.setting

import com.rickaban.android.getrandomusers.app.ui.theme.Theme

sealed interface SettingAction {
    data object NavBack: SettingAction
    data class UpdateTheme(val theme: Theme): SettingAction
    data class UpdateNetworkEnabled(val enabled: Boolean): SettingAction
}                