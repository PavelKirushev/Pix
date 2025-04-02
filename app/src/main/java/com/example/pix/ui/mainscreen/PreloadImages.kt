package com.example.pix.ui.mainscreen

import android.content.Context
import coil3.ImageLoader
import coil3.request.ImageRequest

fun PreloadImages(context: Context, urls: List<String>) {
    val imageLoader = ImageLoader(context)

    urls.forEach { url ->
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()
        imageLoader.enqueue(request)
    }
}