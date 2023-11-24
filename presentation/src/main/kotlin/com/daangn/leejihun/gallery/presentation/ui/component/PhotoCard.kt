package com.daangn.leejihun.gallery.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.R

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
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(photo.downloadUrl)
                .build(),
            contentDescription = stringResource(id = R.string.photo_image),
            contentScale = ContentScale.Crop,
        )
    }
}
