package com.example.pix.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pix.domain.entity.Picture
import com.example.pix.domain.usecases.ClearAllUseCase
import com.example.pix.domain.usecases.GetAllUseCase
import com.example.pix.domain.usecases.InsertAllUseCase
import com.example.pix.domain.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val clearAllUseCase: ClearAllUseCase,
    private val getAllUseCase: GetAllUseCase,
    private val insertAllUseCase: InsertAllUseCase,
    private val searchUseCase: SearchUseCase,
    ): ViewModel() {

    private val _pictures = MutableStateFlow<List<Picture>?>(null)
    val pictures = _pictures.asStateFlow()

    private val _localPictures = MutableStateFlow<List<Picture>?>(null)
    val localPictures = _localPictures.asStateFlow()

    fun getPictureList() = viewModelScope.launch {
        val remotePicture = searchUseCase.search().getOrNull()
        _pictures.value = remotePicture

        remotePicture?.let {
            clearAll()
            insertAll(remotePicture)
        }
    }

    fun getAllPictureDbo() {
        viewModelScope.launch {
            _localPictures.value = getAllUseCase.getAll()
        }
    }

    fun insertAll(pictures: List<Picture>) {
        viewModelScope.launch {
            insertAllUseCase.insertAll(pictures)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            clearAllUseCase.clearAll()
            _localPictures.value = null
        }
    }


}