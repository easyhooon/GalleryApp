package com.daangn.leejihun.gallery.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true)
@Composable
fun LoadStateFooterPreview() {
    LoadStateFooter(
        loadState = LoadState.Loading,
        onRetryClick = {},
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoadStateFooterPreview_DarkMode() {
    LoadStateFooter(
        loadState = LoadState.Loading,
        onRetryClick = {},
    )
}
