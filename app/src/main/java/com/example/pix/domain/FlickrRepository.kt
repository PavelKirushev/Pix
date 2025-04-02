package com.example.pix.domain.entity

interface FlickrRepository {
    suspend fun search(
        text: String = "cats",
        page: Int = 1,
        count: Int = 100
    ): Result<List<Picture>>
}