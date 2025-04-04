package com.example.pix.ui.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.pix.domain.entity.Picture

@Composable
fun AllPicturesScreen(picture: Picture, controller: NavHostController) {
    BoxWithConstraints {
        val size = maxWidth
        AsyncImage(
            model = picture.url,
            contentDescription = picture.title,
            modifier = Modifier
                .size(size)
                .clickable {
                    controller.navigate("details/${picture.id}")
                },
            contentScale = ContentScale.Crop
        )
    }
}