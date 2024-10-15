package com.rickaban.android.getrandomusers.app.data.pref

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.rickaban.android.getrandomusers.BuildConfig
import com.rickaban.android.getrandomusers.app.ui.App
import com.rickaban.android.getrandomusers.app.ui.theme.Theme
import com.rickaban.android.getrandomusers.app.ui.theme.HexColor
import com.rickaban.android.getrandomusers.app.ui.theme.theme


class SharedPref(
    private val pref: SharedPreferences
) {
    companion object{
        private const val APP_THEME = "theme"
        private const val PRIMARY_DARK = "p_dark"
        private const val PRIMARY_LIGHT = "p_light"
        private const val IS_NETWORK_ENABLED = "is_network_enabled"
        private const val BASE_URL = "base_url"

        private lateinit var instance: SharedPref

        fun initTheGet(
            app: App
        ): SharedPref {
            instance = SharedPref(PreferenceManager.getDefaultSharedPreferences(app))
            return instance
        }

        val ViewModel.prefs: SharedPref
            get() = instance
    }

    fun getTheme() = pref.getInt(APP_THEME, Theme.SYSTEM.value).theme()
    @SuppressLint("ApplySharedPref")
    fun setTheme(
        theme: Theme
    ): Boolean {
        return pref.edit().putInt(APP_THEME, theme.value).commit()
    }

    fun getPrimaryDark() = pref.getString(PRIMARY_DARK, "#006699") ?: "#006699"
    @SuppressLint("ApplySharedPref")
    fun setPrimaryDark(primaryDark: HexColor): Boolean {
        return pref.edit().putString(PRIMARY_DARK, primaryDark).commit()
    }

    fun getPrimaryLight() = pref.getString(PRIMARY_LIGHT, "#006699") ?: "#006699"
    @SuppressLint("ApplySharedPref")
    fun setPrimaryLight(primaryLight: HexColor): Boolean {
        return pref.edit().putString(PRIMARY_LIGHT, primaryLight).commit()
    }

    fun isNetworkEnabled() = pref.getBoolean(IS_NETWORK_ENABLED, true)
    @SuppressLint("ApplySharedPref")
    fun updateNetworkEnabled(enabled: Boolean): Boolean {
        return pref.edit().putBoolean(IS_NETWORK_ENABLED, enabled).commit()
    }

    fun getBaseUrl() = pref.getString(BASE_URL, BuildConfig.DEFAULT_BASE_URL) ?: BuildConfig.DEFAULT_BASE_URL
    @SuppressLint("ApplySharedPref")
    fun updateBaseUrl(baseUrl: String): Boolean {
        return pref.edit().putString(BASE_URL, baseUrl).commit()
    }
}        