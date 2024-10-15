package com.rickaban.android.getrandomusers.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rickaban.android.getrandomusers.app.ui.AppActivity
import com.rickaban.android.getrandomusers.app.ui.AppState
import com.rickaban.android.getrandomusers.app.ui.AppViewModel
import timber.log.Timber

@Composable
fun ThemeWrapper(
    state: AppState = (hiltViewModel(viewModelStoreOwner = LocalContext.current as AppActivity) as AppViewModel).state.collectAsStateWithLifecycle().value,
    content: @Composable () -> Unit
) {
    val isDarkColorScheme = when(state.theme) {
        Theme.SYSTEM -> isSystemInDarkTheme()
        Theme.LIGHT -> false
        Theme.DARK -> true
    }
    val colorScheme = if(isDarkColorScheme){
        darkColorScheme(
            primary = Color(android.graphics.Color.parseColor(state.primaryDark))
        )
    }else{
        lightColorScheme(
            primary = Color(android.graphics.Color.parseColor(state.primaryLight))
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}        