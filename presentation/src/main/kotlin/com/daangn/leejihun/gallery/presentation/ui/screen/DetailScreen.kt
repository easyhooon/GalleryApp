@file:OptIn(ExperimentalMaterial3Api::class)

package com.daangn.leejihun.gallery.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.R
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun DetailScreen(
    photo: Photo,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(photo.author)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                            contentDescription = context.getString(R.string.arrow_forward_icon),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(photo.downloadUrl)
                    .build(),
                contentDescription = stringResource(id = R.string.photo_image),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .zoomable(rememberZoomState()),
                contentScale = ContentScale.Fit,
            )
        }
    }
}
