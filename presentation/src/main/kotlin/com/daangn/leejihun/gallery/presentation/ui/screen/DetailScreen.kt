@file:OptIn(ExperimentalMaterial3Api::class)

package com.daangn.leejihun.gallery.presentation.ui.screen

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.daangn.leejihun.gallery.presentation.DetailUiState
import com.daangn.leejihun.gallery.presentation.R
import com.daangn.leejihun.gallery.presentation.model.Photo
import com.daangn.leejihun.gallery.presentation.ui.theme.H5
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import java.io.ByteArrayOutputStream

@Composable
fun DetailScreen(
    uiState: DetailUiState,
    photo: Photo,
    onNavigateBack: () -> Unit,
    saveImageFile: (String, ByteArray) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = photo.author,
                        style = H5,
                    )
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        val byteArray = createByteArrayFromUrl(context, photo)
                        saveImageFile(
                            "IMG_${System.currentTimeMillis()}.png",
                            byteArray,
                        )
                    }
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = stringResource(id = R.string.save_image),
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (LocalInspectionMode.current) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = stringResource(R.string.image_icon),
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
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

                if (uiState.isLoading) {
                    LoadingScreen()
                }
            }
        }
    }
}

suspend fun createByteArrayFromUrl(context: Context, photo: Photo): ByteArray {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(photo.downloadUrl)
        .build()

    val result = (imageLoader.execute(request) as SuccessResult).drawable
    val bitmap = (result as BitmapDrawable).bitmap

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        uiState = DetailUiState(),
        photo = Photo(
            id = "1",
            author = "Christopher Sardegna",
            width = 160,
            height = 160,
            url = "",
            downloadUrl = "",
        ),
        onNavigateBack = {},
        saveImageFile = { _, _ -> },
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview_DarkMode() {
    DetailScreen(
        uiState = DetailUiState(),
        photo = Photo(
            id = "1",
            author = "",
            width = 160,
            height = 160,
            url = "",
            downloadUrl = "",
        ),
        onNavigateBack = {},
        saveImageFile = { _, _ -> },
    )
}
