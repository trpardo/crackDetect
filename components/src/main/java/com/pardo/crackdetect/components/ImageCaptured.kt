package com.pardo.crackdetect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImageCaptured(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    paddingValues: PaddingValues,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    Image(
        modifier = modifier
            .wrapContentSize()
            .padding(paddingValues),
        bitmap = image,
        contentDescription = "Captured image",
        contentScale = contentScale
    )
}
