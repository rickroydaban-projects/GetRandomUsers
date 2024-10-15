package com.rickaban.android.getrandomusers.app.ui

import androidx.lifecycle.ViewModel
import com.rickaban.android.getrandomusers.app.data.pref.SharedPref.Companion.prefs
import com.rickaban.android.getrandomusers.app.data.api.ApiManager.Companion.resetApi
import com.rickaban.android.getrandomusers.app.ui.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(
        AppState(
            isNetworkEnabled = prefs.isNetworkEnabled(),
            theme = prefs.getTheme(),
            primaryLight = prefs.getPrimaryLight(),
            primaryDark = prefs.getPrimaryDark()
        )
    )
    val state = _state.asStateFlow()

    fun updateNetworkEnabled(enabled: Boolean){
        if(prefs.updateNetworkEnabled(enabled)){
            _state.update {
                it.copy(isNetworkEnabled = enabled)
            }

            resetApi(prefs.getBaseUrl(), prefs.isNetworkEnabled())
        }
    }

    fun updateTheme(
        theme: Theme
    ){
        prefs.setTheme(theme)
        _state.update {
            it.copy(theme = theme)
        }
    }

    fun updatePrimaryColors(
        primaryLight: String,
        primaryDark: String
    ){
        _state.update {
            it.copy(
                primaryLight = primaryLight,
                primaryDark = primaryDark
            )
        }
    }

    fun onResume() {

    }

    fun onPause() {

    }
}        