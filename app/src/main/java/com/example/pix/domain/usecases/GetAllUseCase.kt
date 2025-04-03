package com.example.pix.domain.usecases

import com.example.pix.data.room.mappers.toPicture
import com.example.pix.domain.PictureRepository
import com.example.pix.domain.entity.Picture

class GetAllUseCase(private val pictureRepository: PictureRepository) {
    suspend fun getAll(): List<Picture> {
        val result = pictureRepository.getAll().map { it.toPicture() }
        return result
    }
}