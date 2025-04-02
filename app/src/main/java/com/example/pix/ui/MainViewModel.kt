package com.example.pix.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.data.room.PictureDbo
import com.example.pix.data.room.mappers.toPictureDbo
import com.example.pix.domain.PictureRepository
import com.example.pix.domain.entity.FlickrRepository
import com.example.pix.domain.entity.Picture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val flickrRepository: FlickrRepository,
    val pictureRepository: PictureRepository
    ): ViewModel() {

    private val _pictures = MutableStateFlow<List<Picture>?>(null)
    val pictures = _pictures.asStateFlow()

    private val _localPictures = MutableStateFlow<List<PictureDbo>?>(null)
    val localPictures = _localPictures.asStateFlow()

    fun getPictureList() = viewModelScope.launch {
        val remotePicture = flickrRepository.search().getOrNull()
        _pictures.value = remotePicture

        remotePicture?.let {
            clearAll()
            insertAll(remotePicture)
        }
    }

    fun getAllPictureDbo() {
        viewModelScope.launch {
            _localPictures.value = pictureRepository.getAll()
        }
    }

    fun insertAll(pictures: List<Picture>) {
        viewModelScope.launch {
            val picturesDbo = pictures.map{ it.toPictureDbo() }
            pictureRepository.insertAll(picturesDbo)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            pictureRepository.clearAll()
            _localPictures.value = null
        }
    }


}