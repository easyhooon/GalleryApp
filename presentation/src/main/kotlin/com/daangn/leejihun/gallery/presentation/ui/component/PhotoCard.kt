package com.daangn.leejihun.gallery.presentation.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.ui.theme.Gray900
import com.daangn.leejihun.gallery.presentation.ui.theme.TextXsRegular

@Composable
fun PhotoCard(
    photo: Photo,
    onPhotoClick: (Photo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .size(width = 160.dp, height = 160.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onPhotoClick(photo)
            },
    ) {
        Box {
            if (LocalInspectionMode.current) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = "Image Icon",
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(photo.downloadUrl)
                        .build(),
                    contentDescription = stringResource(id = R.string.photo_image),
                    contentScale = ContentScale.Crop,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Gray900.copy(alpha = 0.6f))
                    .align(Alignment.BottomStart)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    color = Color.White,
                    text = photo.author,
                    style = TextXsRegular,
                )
            }
        }
    }
}

@Preview()
@Composable
fun PhotoCardPreview() {
    PhotoCard(
        photo = Photo(
            id = "1",
            author = "Christopher Sardegna",
            width = 160,
            height = 160,
            url = "",
            downloadUrl = "",
        ),
        onPhotoClick = {},
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PhotoCardPreview_DarkMode() {
    PhotoCard(
        photo = Photo(
            id = "1",
            author = "Christopher Sardegna",
            width = 160,
            height = 160,
            url = "",
            downloadUrl = "",
        ),
        onPhotoClick = {},
    )
}
