package com.example.pix.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.pix.data.flickr.FlickrRepositoryImpl
import com.example.pix.data.flickr.FlickrRetrofit
import com.example.pix.data.room.PictureDao
import com.example.pix.data.room.PictureDatabase
import com.example.pix.data.room.PictureRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            context = this,
            PictureDatabase::class.java,
            "picture_database"
        ).build()
        val mainViewModel = MainViewModel(
            flickrRepository = FlickrRepositoryImpl(flickrApi = FlickrRetrofit.api.value),
            pictureRepository = PictureRepositoryImpl(pictureDao = db.getPictureDao())
            )
        setContent {
            Main(mainViewModel = mainViewModel)
        }
    }
}