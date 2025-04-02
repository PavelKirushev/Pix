package com.example.pix.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pix.data.room.mappers.toPicture

@Composable
fun Main(mainViewModel: MainViewModel) {
    val remotePictures by mainViewModel.pictures.collectAsState()
    val localPictures by mainViewModel.localPictures.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getPictureList()
        mainViewModel.getAllPictureDbo()
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ){
        when {
            remotePictures != null -> {
                items(remotePictures ?: emptyList()){ picture ->
                    AllPicturesScreen(picture = picture)
                }
            }
            localPictures != null -> {
                items(localPictures ?: emptyList()){ pictureDbo ->
                    AllPicturesScreen(picture = pictureDbo.toPicture())
                }
            }
            else -> {
                item {
                    Text(
                        text = "Загрузка...",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

            }
        }
    }
}