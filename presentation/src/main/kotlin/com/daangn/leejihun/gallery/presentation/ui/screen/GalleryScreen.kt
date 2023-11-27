@file:OptIn(ExperimentalMaterial3Api::class)

package com.daangn.leejihun.gallery.presentation.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.ItemSnapshotList
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.daangn.leejihun.gallery.presentation.GalleryUiState
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.ui.component.FilteredPhotoLazyVerticalGrid
import com.daangn.leejihun.gallery.presentation.ui.component.PhotoLazyVerticalGrid
import com.daangn.leejihun.gallery.presentation.ui.component.SearchTextField
import com.daangn.leejihun.gallery.presentation.ui.component.TopBarTitle
import com.daangn.leejihun.gallery.presentation.ui.theme.Gray900
import kotlinx.coroutines.flow.MutableStateFlow
import my.nanihadesuka.compose.LazyGridVerticalScrollbar

@Composable
fun GalleryScreen(
    photoList: LazyPagingItems<Photo>,
    uiState: GalleryUiState,
    searchQuery: TextFieldValue,
    updateSearchQuery: (TextFieldValue) -> Unit,
    onPhotoClick: (Photo) -> Unit,
    toggleSearchVisibility: () -> Unit,
    getCurrentPhotoListSnapshot: (ItemSnapshotList<Photo>) -> Unit,
    onSearchQuery: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyGridState = rememberLazyGridState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val isLoading = photoList.loadState.refresh is LoadState.Loading
    val isError = photoList.loadState.refresh is LoadState.Error

    LaunchedEffect(key1 = uiState.isSearchVisible) {
        if (uiState.isSearchVisible) {
            getCurrentPhotoListSnapshot(photoList.itemSnapshotList)
        }
    }

    LaunchedEffect(key1 = searchQuery.text) {
        if (uiState.isSearchVisible && searchQuery.text == "") {
            keyboardController?.hide()
            onSearchQuery(TextFieldValue(""))
            lazyGridState.animateScrollToItem(0)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(
                        loadState = photoList.loadState.refresh,
                        toggleSearchVisibility = toggleSearchVisibility,
                        isSearchVisible = uiState.isSearchVisible,
                    )
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            Column {
                AnimatedVisibility(visible = uiState.isSearchVisible) {
                    SearchTextField(
                        searchQuery = searchQuery,
                        updateSearchQuery = updateSearchQuery,
                        onSearchQuery = onSearchQuery,
                    )
                }
                if (uiState.isSearchVisible) {
                    LazyGridVerticalScrollbar(
                        state = lazyGridState,
                        thumbColor = Gray900,
                        thumbSelectedColor = colorScheme.primary,
                        thickness = 8.dp,
                    ) {
                        if (uiState.filteredPhotoList.isEmpty()) {
                            EmptyScreen()
                        } else {
                            FilteredPhotoLazyVerticalGrid(
                                filteredPhotoList = uiState.filteredPhotoList,
                                lazyGridState = lazyGridState,
                                onPhotoClick = onPhotoClick,
                            )
                        }
                    }
                } else {
                    when {
                        isLoading -> LoadingScreen()
                        isError -> ErrorScreen(onClickRetryButton = { photoList.retry() })
                        else -> {
                            PhotoLazyVerticalGrid(
                                photoList = photoList,
                                lazyGridState = lazyGridState,
                                onPhotoClick = onPhotoClick,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    val photos = mutableListOf<Photo>()
    for (i in 1..8) {
        photos.add(
            Photo(
                id = "$i",
                author = "Christopher Sardegna",
                width = 160,
                height = 160,
                url = "",
                downloadUrl = "",
            ),
        )
    }
    val photoList = MutableStateFlow(PagingData.from(photos)).collectAsLazyPagingItems()

    GalleryScreen(
        photoList = photoList,
        uiState = GalleryUiState(),
        searchQuery = TextFieldValue(""),
        updateSearchQuery = {},
        onPhotoClick = {},
        toggleSearchVisibility = {},
        getCurrentPhotoListSnapshot = {},
        onSearchQuery = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GalleryScreenPreview_DarkMode() {
    val photos = mutableListOf<Photo>()
    for (i in 1..8) {
        photos.add(
            Photo(
                id = "$i",
                author = "Christopher Sardegna",
                width = 160,
                height = 160,
                url = "",
                downloadUrl = "",
            ),
        )
    }
    val photoList = MutableStateFlow(PagingData.from(photos)).collectAsLazyPagingItems()

    GalleryScreen(
        photoList = photoList,
        uiState = GalleryUiState(),
        searchQuery = TextFieldValue(""),
        updateSearchQuery = {},
        onPhotoClick = {},
        toggleSearchVisibility = {},
        getCurrentPhotoListSnapshot = {},
        onSearchQuery = {},
    )
}
