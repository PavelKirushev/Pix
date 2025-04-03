package com.example.pix.domain.usecases

import com.example.pix.data.room.mappers.toPictureDbo
import com.example.pix.domain.PictureRepository
import com.example.pix.domain.entity.Picture

class InsertAllUseCase(private val pictureRepository: PictureRepository) {
    suspend fun insertAll(users: List<Picture>) {
        val result = users.map { it.toPictureDbo() }
        pictureRepository.insertAll(result)
    }
}