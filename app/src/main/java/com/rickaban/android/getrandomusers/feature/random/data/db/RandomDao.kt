package com.rickaban.android.getrandomusers.feature.random.data.db
        
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rickaban.android.getrandomusers.feature.random.data.db.local.RandomUserLocal
import com.rickaban.android.getrandomusers.feature.random.data.db.local.RandomUserDetailLocal

@Dao
interface RandomDao {

    @Query("SELECT * FROM random_user")
    suspend fun getRandomUserList(): List<RandomUserLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRandomUserList(randomUserList: List<RandomUserLocal>)

    @Query("SELECT * FROM random_user_detail WHERE randomUserID=:randomUserID")
    suspend fun getRandomUserDetail(randomUserID: Long): RandomUserDetailLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRandomUserDetail(randomUserDetail: RandomUserDetailLocal)
}
        