package com.example.pix.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PictureDbo>)

    @Query("DELETE FROM pictures")
    suspend fun clearAll()

    @Query("SELECT * FROM pictures")
    suspend fun getAll(): List<PictureDbo>
}