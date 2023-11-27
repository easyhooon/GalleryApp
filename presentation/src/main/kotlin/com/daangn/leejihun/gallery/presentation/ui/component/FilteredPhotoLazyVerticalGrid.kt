@file:OptIn(ExperimentalFoundationApi::class)

package com.daangn.leejihun.gallery.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.daangn.leejihun.gallery.presentation.model.Photo

@Composable
fun FilteredPhotoLazyVerticalGrid(
    filteredPhotoList: List<Photo>,
    lazyGridState: LazyGridState,
    onPhotoClick: (Photo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = min(screenWidth, 160.dp)),
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                if (lazyGridState.isScrollInProgress) {
                    keyboardController?.hide()
                }
            },
        state = lazyGridState,
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = filteredPhotoList,
            key = { it.id },
        ) { photo ->
            PhotoCard(
                photo = photo,
                onPhotoClick = onPhotoClick,
                modifier = Modifier.animateItemPlacement(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing,
                    ),
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilteredPhotoLazyVerticalGridPreview() {
    FilteredPhotoLazyVerticalGrid(
        filteredPhotoList = listOf(
            Photo(
                id = "44",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "45",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "46",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "47",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "48",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "49",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "50",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "51",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "52",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "53",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
        ),
        lazyGridState = rememberLazyGridState(),
        onPhotoClick = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FilteredPhotoLazyVerticalGridPreview_DarkMode() {
    FilteredPhotoLazyVerticalGrid(
        filteredPhotoList = listOf(
            Photo(
                id = "44",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "45",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "46",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "47",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "48",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "49",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "50",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "51",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "52",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
            Photo(
                id = "53",
                author = "Christopher Sardegna",
                width = 4272,
                height = 2848,
                url = "https://unsplash.com/photos/R1E6x8U83Ho",
                downloadUrl = "https://picsum.photos/id/44/4272/2848",
            ),
        ),
        lazyGridState = rememberLazyGridState(),
        onPhotoClick = {},
    )
}
