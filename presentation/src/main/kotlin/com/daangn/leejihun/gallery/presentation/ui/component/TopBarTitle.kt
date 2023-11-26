package com.daangn.leejihun.gallery.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.ui.theme.H5
import com.daangn.leejihun.gallery.presentation.ui.theme.Title

@Composable
fun TopBarTitle(
    loadState: LoadState,
    toggleSearchVisibility: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.gallery),
            style = H5,
        )
        Spacer(modifier = Modifier.weight(1f))
        if (loadState != LoadState.Loading) {
            TextButton(
                onClick = toggleSearchVisibility,
            ) {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colorScheme.secondary,
                    style = Title,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarTitlePreview() {
    TopBarTitle(
        loadState = LoadState.Loading,
        toggleSearchVisibility = {}
    )
}
