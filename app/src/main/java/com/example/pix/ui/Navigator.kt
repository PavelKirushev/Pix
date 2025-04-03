package com.example.pix.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pix.ui.mainscreen.MainScreen
import com.example.pix.ui.picturescreen.PictureScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Navigator(mainViewModel: MainViewModel, controller: NavHostController, ){
    NavHost(navController = controller, startDestination = "home") {
        composable("home") { MainScreen(mainViewModel, controller) }
        composable("details/{pictureId}") {
            val pictureId = it.arguments?.getString("pictureId")
            if (pictureId != null) {
                val picture = mainViewModel.localPictures.value?.find { it.id == pictureId }
                if (picture != null) {
                    PictureScreen(picture = picture)
                }
            }

        }
    }
}