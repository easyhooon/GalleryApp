@file:OptIn(ExperimentalMaterial3Api::class)


package com.daangn.leejihun.gallery.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.daangn.leejihun.gallery.model.Photo
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.ui.component.PhotoCard

@Composable
fun GalleryScreen(
    photoList: LazyPagingItems<Photo>,
    onPhotoClick: (Photo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val isLoading = photoList.loadState.refresh is LoadState.Loading
    val isError = photoList.loadState.refresh is LoadState.Error

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            when {
                isLoading -> LoadingScreen()
                isError -> ErrorScreen(onClickRetryButton = { photoList.retry() })
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = min(screenWidth, 160.dp)),
                        modifier = modifier.fillMaxSize(),
                        contentPadding = PaddingValues(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(
                            count = photoList.itemCount,
                            key = photoList.itemKey(key = { it.id }),
                            contentType = photoList.itemContentType(),
                        ) { index ->
                            photoList[index]?.let { photo ->
                                PhotoCard(
                                    photo = photo,
                                    onPhotoClick = onPhotoClick,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
