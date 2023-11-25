@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.daangn.leejihun.gallery.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.paging.ItemSnapshotList
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.daangn.leejihun.gallery.presentation.GalleryUiState
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.ui.component.PhotoCard
import com.daangn.leejihun.gallery.presentation.ui.theme.Gray900
import com.daangn.leejihun.gallery.presentation.ui.theme.H5
import com.daangn.leejihun.gallery.presentation.ui.theme.TextLMedium
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
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
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
            lazyGridState.animateScrollToItem(0)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(id = R.string.gallery),
                            style = H5,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = toggleSearchVisibility,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(R.string.search_icon),
                                modifier = Modifier.size(32.dp),
                            )
                        }
                    }

                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            Column {
                AnimatedVisibility(visible = uiState.isSearchVisible) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = searchQuery,
                        singleLine = true,
                        onValueChange = updateSearchQuery,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(R.string.search_icon),
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.text.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Outlined.Cancel,
                                    contentDescription = stringResource(R.string.search_icon),
                                    modifier.clickable {
                                        updateSearchQuery(TextFieldValue(""))
                                    },
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.author_search),
                                style = TextLMedium,
                            )
                        },
                    )
                }
                if (uiState.isSearchVisible) {
                    LazyGridVerticalScrollbar(
                        state = lazyGridState,
                        thumbColor = Gray900,
                        thumbSelectedColor = colorScheme.primary,
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
                                items = uiState.filteredPhotoList,
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
                } else {
                    when {
                        isLoading -> LoadingScreen()
                        isError -> ErrorScreen(onClickRetryButton = { photoList.retry() })
                        else -> {
                            LazyGridVerticalScrollbar(
                                state = lazyGridState,
                                thumbColor = Gray900,
                                thumbSelectedColor = colorScheme.primary,
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
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
