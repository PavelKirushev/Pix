package com.example.pix.ui.picturescreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun TextScreen(text: String) {
    Text(text = text, fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
}