package com.kenshi.gallery.presentation.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kenshi.gallery.presentation.R
import com.kenshi.gallery.presentation.ui.theme.TextLMedium
import com.kenshi.gallery.presentation.ui.theme.TextSRegular

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
            Spacer(modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.empty_message_description),
                style = TextSRegular,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenLightPreview() {
    EmptyScreen()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenLightPreview_DarkMode() {
    EmptyScreen()
}
