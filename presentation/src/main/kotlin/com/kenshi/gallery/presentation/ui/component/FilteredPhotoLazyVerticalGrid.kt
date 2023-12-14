@file:OptIn(ExperimentalFoundationApi::class)

package com.kenshi.gallery.presentation.ui.component

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
import com.kenshi.gallery.presentation.model.Photo

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
    val photos = mutableListOf<Photo>()
    for (i in 1..10) {
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

    FilteredPhotoLazyVerticalGrid(
        filteredPhotoList = photos,
        lazyGridState = rememberLazyGridState(),
        onPhotoClick = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FilteredPhotoLazyVerticalGridPreview_DarkMode() {
    val photos = mutableListOf<Photo>()
    for (i in 1..10) {
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

    FilteredPhotoLazyVerticalGrid(
        filteredPhotoList = photos,
        lazyGridState = rememberLazyGridState(),
        onPhotoClick = {},
    )
}
