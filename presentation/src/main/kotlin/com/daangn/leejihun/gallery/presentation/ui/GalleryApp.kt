package com.daangn.leejihun.gallery.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.BundleCompat
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.daangn.leejihun.gallery.presentation.DetailUiEvent
import com.daangn.leejihun.gallery.presentation.DetailViewModel
import com.daangn.leejihun.gallery.presentation.GalleryUiEvent
import com.daangn.leejihun.gallery.presentation.GalleryViewModel
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.ui.screen.DetailScreen
import com.daangn.leejihun.gallery.presentation.ui.screen.GalleryScreen
import com.daangn.leejihun.gallery.presentation.ui.theme.GalleryAppTheme
import com.daangn.leejihun.gallery.presentation.util.ObserveAsEvents

sealed class Destination(val route: String) {
    val id get() = "android-app://androidx.navigation/$route".hashCode()

    data object Gallery : Destination("gallery")
    data object Detail : Destination("detail")
}

@Composable
fun GalleryApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    GalleryAppTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color.White,
        ) {
            NavHost(
                navController = navController,
                startDestination = "gallery",
            ) {
                composable(Destination.Gallery.route) {
                    val viewModel = hiltViewModel<GalleryViewModel>()
                    val photoList = viewModel.photoList.collectAsLazyPagingItems()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
                        when (event) {
                            is GalleryUiEvent.OnNavigateDetail -> {
                                navController.navigate(
                                    resId = Destination.Detail.id,
                                    args = bundleOf(
                                        "photo" to Photo(
                                            id = event.photo.id,
                                            author = event.photo.author,
                                            width = event.photo.width,
                                            height = event.photo.height,
                                            url = event.photo.url,
                                            downloadUrl = event.photo.downloadUrl,
                                        ),
                                    ),
                                )
                            }
                        }
                    }

                    GalleryScreen(
                        photoList = photoList,
                        uiState = uiState,
                        searchQuery = viewModel.searchQuery,
                        updateSearchQuery = viewModel::updateSearchQuery,
                        onPhotoClick = viewModel::onNavigateDetail,
                        toggleSearchVisibility = viewModel::toggleSearchVisibility,
                        getCurrentPhotoListSnapshot = viewModel::getCurrentPhotoListSnapshot,
                        onSearchQuery = viewModel::onSearchQuery,
                    )
                }

                composable(Destination.Detail.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry("gallery")
                    }

                    val args = backStackEntry.arguments ?: Bundle()
                    val photo = BundleCompat.getParcelable(args, "photo", Photo::class.java)

                    val viewModel = hiltViewModel<DetailViewModel>(parentEntry)
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val context = LocalContext.current

                    ObserveAsEvents(flow = viewModel.eventFlow) { event ->
                        when (event) {
                            is DetailUiEvent.OnNavigateBack -> {
                                navController.popBackStack()
                            }

                            is DetailUiEvent.ShowToast -> {
                                Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    if (photo != null) {
                        DetailScreen(
                            uiState = uiState,
                            photo = photo,
                            onNavigateBack = viewModel::onNavigateBack,
                            saveImageFile = viewModel::saveImageFile,
                        )
                    }
                }
            }
        }
    }
}
