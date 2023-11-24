package com.daangn.leejihun.gallery.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.daangn.leejihun.gallery.model.Photo

@Composable
fun DetailScreen(
    photo: Photo,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Text("$photo")
    }
}
