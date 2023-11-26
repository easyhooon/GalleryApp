package com.daangn.leejihun.gallery.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.daangn.leejihun.gallery.presentation.ui.screen.EndOfResultScreen
import com.daangn.leejihun.gallery.presentation.ui.screen.LoadErrorScreen
import com.daangn.leejihun.gallery.presentation.ui.screen.LoadingScreen

@Composable
fun LoadStateFooter(
    loadState: LoadState,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        when (loadState) {
            is LoadState.Loading -> LoadingScreen()

            is LoadState.Error -> LoadErrorScreen(onRetryClick = onRetryClick)

            else -> EndOfResultScreen()
        }
    }
}
