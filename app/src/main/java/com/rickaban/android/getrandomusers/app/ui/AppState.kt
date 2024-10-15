package com.rickaban.android.getrandomusers.app.ui

import com.rickaban.android.getrandomusers.app.ui.theme.Theme

data class AppState(
    val isNetworkEnabled: Boolean,
    val theme: Theme,
    val primaryLight: String,
    val primaryDark: String,
)        