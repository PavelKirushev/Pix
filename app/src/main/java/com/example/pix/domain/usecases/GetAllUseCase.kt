package com.example.pix.domain.usecases

import com.example.pix.data.room.PictureDbo
import com.example.pix.domain.PictureRepository

class GetAllUseCase(private val pictureRepository: PictureRepository) {
    suspend fun getAll(): List<PictureDbo> {
        return pictureRepository.getAll()
    }
}