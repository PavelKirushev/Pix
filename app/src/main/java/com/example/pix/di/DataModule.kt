package com.example.pix.di

import android.content.Context
import androidx.room.Room
import com.example.pix.data.flickr.FlickrApi
import com.example.pix.data.flickr.FlickrRepositoryImpl
import com.example.pix.data.flickr.FlickrRetrofit
import com.example.pix.data.room.PictureDao
import com.example.pix.data.room.PictureDatabase
import com.example.pix.data.room.PictureRepositoryImpl
import com.example.pix.domain.PictureRepository
import com.example.pix.domain.entity.FlickrRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideFlickrRepository(flickrApi: FlickrApi): FlickrRepository = FlickrRepositoryImpl(flickrApi = flickrApi)

    @Provides
    @Singleton
    fun provideFlickrApi(): FlickrApi = FlickrRetrofit.api.value

    @Provides
    @Singleton
    fun providePictureRepository(pictureDao: PictureDao): PictureRepository  = PictureRepositoryImpl(pictureDao = pictureDao)

    @Provides
    @Singleton
    fun providePictureDao(pictureDatabase: PictureDatabase): PictureDao = pictureDatabase.getPictureDao()

    @Provides
    @Singleton
    fun providePictureDatabase(@ApplicationContext context: Context): PictureDatabase {
        val database = Room.databaseBuilder(
            context = context,
            PictureDatabase::class.java,
            "picture_database"
        ).build()
        return database
    }
}


