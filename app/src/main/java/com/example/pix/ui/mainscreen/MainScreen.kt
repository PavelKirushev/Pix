package com.example.pix.ui.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pix.data.room.mappers.toPicture
import com.example.pix.ui.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(mainViewModel: MainViewModel, controller: NavHostController) {
    val remotePictures by mainViewModel.pictures.collectAsState()
    val localPictures by mainViewModel.localPictures.collectAsState()
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        launch {
            mainViewModel.getPictureList()
            mainViewModel.getAllPictureDbo()
        }
    }

    LaunchedEffect(remotePictures, localPictures) {
        remotePictures?.let { PreloadImages(context, it.map { picture -> picture.url }) }
        localPictures?.let { PreloadImages(context, it.map { picture -> picture.url }) }
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
    ) {
        when {
            remotePictures != null -> {
                mainViewModel.getAllPictureDbo()
                items(remotePictures!!) { picture ->
                    AllPicturesScreen(picture = picture, controller)
                }
            }
            localPictures != null -> {
                items(localPictures!!) { pictureDbo ->
                    AllPicturesScreen(picture = pictureDbo.toPicture(), controller)
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

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                val preloadIndex = index + 5
                if (remotePictures != null && preloadIndex < remotePictures!!.size) {
                    val url = remotePictures!![preloadIndex].url
                    PreloadImages(context, listOf(url))
                }
            }
    }
}