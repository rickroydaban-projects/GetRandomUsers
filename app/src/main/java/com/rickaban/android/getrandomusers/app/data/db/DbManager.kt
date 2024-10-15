package com.rickaban.android.getrandomusers.app.data.db

import android.content.Context
import androidx.room.Room
import com.rickaban.android.getrandomusers.feature.random.data.db.RandomDao
import com.rickaban.android.getrandomusers.feature.random.domain.AppRandomRepository

class DBManager {
    companion object {
        private lateinit var instance: AppDatabase
        val AppRandomRepository.randomDao: RandomDao
            get() = instance.randomDao()

        fun init(context: Context) {
            instance = Room
                .databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppDatabase.NAME
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}        