package com.example.pix.domain

import com.example.pix.data.room.PictureDbo

interface PictureRepository {
    suspend fun insertAll(users: List<PictureDbo>)

    suspend fun clearAll()

    suspend fun getAll(): List<PictureDbo>
}