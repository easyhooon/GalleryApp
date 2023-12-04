package com.daangn.leejihun.gallery.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.ui.theme.H5
import com.daangn.leejihun.gallery.presentation.ui.theme.Title

@Composable
fun TopBarTitle(
    loadState: LoadState,
    toggleSearchVisibility: () -> Unit,
    isSearchVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val rotationState by animateFloatAsState(targetValue = if (isSearchVisible) 180f else 0f)

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
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable {
                        toggleSearchVisibility()
                    }
                    .padding(8.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_drop_down),
                        contentDescription = stringResource(R.string.dropdown),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.rotate(rotationState),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.search),
                        color = MaterialTheme.colorScheme.secondary,
                        style = Title,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TopBarTitlePreview() {
    TopBarTitle(
        loadState = LoadState.Loading,
        toggleSearchVisibility = {},
        isSearchVisible = true,
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarTitlePreview_DarkMode() {
    TopBarTitle(
        loadState = LoadState.Loading,
        toggleSearchVisibility = {},
        isSearchVisible = true,
    )
}
