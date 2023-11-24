package com.daangn.leejihun.gallery.presentation.ui

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.os.BundleCompat
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.daangn.leejihun.gallery.model.Photo
import com.daangn.leejihun.gallery.presentation.GalleryViewModel
import com.daangn.leejihun.gallery.presentation.ui.screen.DetailScreen
import com.daangn.leejihun.gallery.presentation.ui.screen.GalleryScreen
import com.daangn.leejihun.gallery.presentation.ui.theme.GalleryAppTheme

sealed class Destination(val route: String) {
    val id get() = "android-app://androidx.navigation/$route".hashCode()

    data object Gallery : Destination("gallery")
    data object Detail : Destination("detail")
}

@Composable
fun GalleryApp(
    viewModel: GalleryViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val photoList = viewModel.photoList.collectAsLazyPagingItems()

    GalleryAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
        ) {
            NavHost(
                navController = navController,
                startDestination = "gallery",
            ) {
                composable(Destination.Gallery.route) {
                    GalleryScreen(
                        photoList = photoList,
                        onPhotoClick = {
                            navController.navigate(
                                resId = Destination.Detail.id,
                                args = bundleOf(
                                    "photo" to Photo(
                                        id = it.id,
                                        author = it.author,
                                        width = it.width,
                                        height = it.height,
                                        url = it.url,
                                        downloadUrl = it.downloadUrl,
                                    ),
                                ),
                            )
                        },
                    )
                }
                composable(Destination.Detail.route) { entry ->
                    val args = entry.arguments ?: Bundle()
                    val photo = BundleCompat.getParcelable(args, "photo", Photo::class.java)
                    if (photo != null) {
                        DetailScreen(photo = photo)
                    }
                }
            }
        }
    }
}
