package com.daangn.leejihun.gallery.presentation.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.daangn.leejihun.gallery.model.Photo

@Composable
fun DetailScreen(
    photo: Photo,
    modifier: Modifier = Modifier,
) {
    Text("$photo")
}
