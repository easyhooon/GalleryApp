package com.kenshi.gallery.presentation.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kenshi.gallery.presentation.R
import com.kenshi.gallery.presentation.ui.theme.TextLMedium
import com.kenshi.gallery.presentation.ui.theme.Title

@Composable
fun ErrorScreen(
    onClickRetryButton: () -> Unit,
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
                text = stringResource(id = R.string.error_message),
                style = Title,
            )
            Spacer(modifier.height(12.dp))
            Button(onClick = onClickRetryButton) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = TextLMedium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        onClickRetryButton = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorScreenPreview_DarkMode() {
    ErrorScreen(
        onClickRetryButton = {},
    )
}
