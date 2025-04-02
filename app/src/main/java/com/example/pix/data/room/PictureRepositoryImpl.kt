package com.example.pix.data.room

import com.example.pix.domain.PictureRepository

class PictureRepositoryImpl(val pictureDao: PictureDao): PictureRepository {
    override suspend fun insertAll(users: List<PictureDbo>) {
        pictureDao.insertAll(users)
    }

    override suspend fun clearAll() {
        pictureDao.clearAll()
    }

    override suspend fun getAll(): List<PictureDbo> {
        return pictureDao.getAll()
    }
}