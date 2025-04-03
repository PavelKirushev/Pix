package com.example.pix.domain.usecases

import com.example.pix.domain.PictureRepository

class ClearAllUseCase(private val pictureRepository: PictureRepository) {
    suspend fun clearAll() {
        pictureRepository.clearAll()
    }
}