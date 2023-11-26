package com.daangn.leejihun.gallery.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.ui.theme.TextLMedium

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.empty_message),
                style = TextLMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenLightPreview() {
    EmptyScreen()
}
