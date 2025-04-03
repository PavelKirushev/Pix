package com.example.pix.di

import com.example.pix.domain.PictureRepository
import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.usecases.ClearAllUseCase
import com.example.pix.domain.usecases.GetAllUseCase
import com.example.pix.domain.usecases.InsertAllUseCase
import com.example.pix.domain.usecases.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideClearAllUseCase(pictureRepository: PictureRepository): ClearAllUseCase = ClearAllUseCase(pictureRepository)

    @Provides
    fun provideGetAllUseCase(pictureRepository: PictureRepository): GetAllUseCase = GetAllUseCase(pictureRepository)

    @Provides
    fun provideInsertAllUseCase(pictureRepository: PictureRepository): InsertAllUseCase = InsertAllUseCase(pictureRepository)

    @Provides
    fun provideSearchUseCase(flickrRepository: FlickrRepository): SearchUseCase = SearchUseCase(flickrRepository)

}