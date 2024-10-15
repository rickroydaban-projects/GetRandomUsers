package com.rickaban.android.getrandomusers.app.data.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.rickaban.android.getrandomusers.feature.random.data.db.RandomDao
import com.rickaban.android.getrandomusers.feature.random.data.db.local.RandomUserLocal
import com.rickaban.android.getrandomusers.feature.random.data.db.local.RandomUserDetailLocal

@Database(
    version = 1,
    entities = [
        SampleEntity::class,
        RandomUserLocal::class,
        RandomUserDetailLocal::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "db"
    }

    abstract fun randomDao(): RandomDao
}

//TODO delete later when a table is available
@Entity
data class SampleEntity(
    @PrimaryKey val id: Long
)        