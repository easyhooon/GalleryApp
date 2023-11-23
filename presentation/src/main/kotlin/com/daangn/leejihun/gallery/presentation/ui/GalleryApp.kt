package com.daangn.leejihun.gallery.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.daangn.leejihun.gallery.presentation.GalleryViewModel

@Composable
fun GalleryApp(
    viewModel: GalleryViewModel = hiltViewModel(),
) {
    val photoList = viewModel.photoList.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {
        GalleryScreen(photoList = photoList)
    }
}
