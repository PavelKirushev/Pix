package com.example.pix.domain.usecases

import com.example.pix.data.room.PictureDbo
import com.example.pix.domain.PictureRepository

class InsertAllUseCase(private val pictureRepository: PictureRepository) {
    suspend fun insertAll(users: List<PictureDbo>) {
        pictureRepository.insertAll(users)
    }
}