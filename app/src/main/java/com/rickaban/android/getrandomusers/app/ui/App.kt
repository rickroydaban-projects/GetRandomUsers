package com.rickaban.android.getrandomusers.app.ui

import android.app.Application
import androidx.work.WorkManager
import com.rickaban.android.getrandomusers.BuildConfig
import com.rickaban.android.getrandomusers.app.data.pref.SharedPref
import com.rickaban.android.getrandomusers.app.data.api.ApiManager
import com.rickaban.android.getrandomusers.app.data.db.DBManager
import com.rickaban.android.getrandomusers.app.ui.file.FileDownloader
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        val prefs = SharedPref.initTheGet(this)

        ApiManager.init(prefs.getBaseUrl(), prefs.isNetworkEnabled())
        DBManager.init(this)
//TODO when done setup with firebase        FirebaseApp.initializeApp(this)
        FileDownloader.init(this)
        WorkManager.getInstance(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}        