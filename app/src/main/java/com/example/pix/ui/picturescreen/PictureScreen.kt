package com.example.pix.ui.picturescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.pix.domain.entity.Picture

@Composable
fun PictureScreen(picture: Picture) {
    Column {
        AsyncImage(
            model = picture.url,
            contentDescription = picture.title,
            modifier = Modifier
                .fillMaxWidth(0.3f)
        )
        TextScreen(text = "Описание: ${picture.title}")
        TextScreen(text = "Ссылка: ${picture.url}")
    }
}