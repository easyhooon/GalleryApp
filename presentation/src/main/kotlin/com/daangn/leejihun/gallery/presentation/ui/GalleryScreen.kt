package com.daangn.leejihun.gallery.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.daangn.leejihun.gallery.domain.entity.PhotoEntity

@Composable
fun GalleryScreen(
    photoList: LazyPagingItems<PhotoEntity>,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val isLoading = photoList.loadState.refresh is LoadState.Loading
    val isError = photoList.loadState.refresh is LoadState.Error

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
                        PhotoCard(photo = photo)
                    }
                }
            }
        }
    }
}
