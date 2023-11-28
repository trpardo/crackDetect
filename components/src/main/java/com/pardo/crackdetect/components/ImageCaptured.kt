package com.pardo.crackdetect.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImageCaptured(
    image: Bitmap,
    paddingValues: PaddingValues
) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        bitmap = image.asImageBitmap(),
        contentDescription = "Captured image",
        contentScale = ContentScale.FillWidth
    )
}
