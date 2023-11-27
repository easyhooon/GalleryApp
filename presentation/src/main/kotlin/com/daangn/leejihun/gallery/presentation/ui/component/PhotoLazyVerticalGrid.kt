package com.daangn.leejihun.gallery.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.ui.theme.Gray900
import kotlinx.coroutines.flow.MutableStateFlow
import my.nanihadesuka.compose.LazyGridVerticalScrollbar

@Composable
fun PhotoLazyVerticalGrid(
    photoList: LazyPagingItems<Photo>,
    lazyGridState: LazyGridState,
    onPhotoClick: (Photo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyGridVerticalScrollbar(
        state = lazyGridState,
        thumbColor = Gray900,
        thumbSelectedColor = MaterialTheme.colorScheme.primary,
        thickness = 8.dp,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = min(screenWidth, 160.dp)),
            modifier = modifier.fillMaxSize(),
            state = lazyGridState,
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

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                },
            ) {
                LoadStateFooter(
                    loadState = photoList.loadState.append,
                    onRetryClick = { photoList.retry() },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoLazyVerticalGridPreview() {
    val photoList = MutableStateFlow(
        PagingData.from(
            listOf(
                Photo(
                    id = "44",
                    author = "Christopher Sardegna",
                    width = 4272,
                    height = 2848,
                    url = "https://unsplash.com/photos/R1E6x8U83Ho",
                    downloadUrl = "https://picsum.photos/id/44/4272/2848",
                ),
            ),
        ),
    ).collectAsLazyPagingItems()

    PhotoLazyVerticalGrid(
        photoList = photoList,
        lazyGridState = rememberLazyGridState(),
        onPhotoClick = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PhotoLazyVerticalGridPreview_DarkMode() {
    val photoList = MutableStateFlow(
        PagingData.from(
            listOf(
                Photo(
                    id = "44",
                    author = "Christopher Sardegna",
                    width = 4272,
                    height = 2848,
                    url = "https://unsplash.com/photos/R1E6x8U83Ho",
                    downloadUrl = "https://picsum.photos/id/44/4272/2848",
                ),
            ),
        ),
    ).collectAsLazyPagingItems()

    PhotoLazyVerticalGrid(
        photoList = photoList,
        lazyGridState = rememberLazyGridState(),
        onPhotoClick = {},
    )
}
