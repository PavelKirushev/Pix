package com.example.pix.domain.usecases

import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.entity.Picture

class SearchUseCase(private val flickrRepository: FlickrRepository) {
    suspend fun search(): Result<List<Picture>> {
        return flickrRepository.search()
    }
}