package com.kenshi.gallery.presentation.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kenshi.gallery.presentation.R
import com.kenshi.gallery.presentation.ui.theme.TextMRegular

@Composable
fun LoadErrorScreen(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.error_message),
                style = TextMRegular,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
            Button(
                onClick = onRetryClick,
            ) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = TextMRegular,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadErrorScreenPreview() {
    LoadErrorScreen(
        onRetryClick = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoadErrorScreenPreview_DarkMode() {
    LoadErrorScreen(
        onRetryClick = {},
    )
}
