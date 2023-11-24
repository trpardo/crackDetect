package com.pardo.crackdetect.analysis.ui

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.pardo.crackdetect.analysis.CameraState
import com.pardo.crackdetect.analysis.CameraViewModel
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.components.Buttons
import com.pardo.crackdetect.components.ScreenContainer
import com.pardo.crackdetect.theme.CrackDetectTheme
import com.pardo.crackdetect.theme.R
import java.util.concurrent.Executor

@Composable
fun AnalysisCameraScreen(
    navigator: AnalysisNavigator? = null,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val cameraState: CameraState by viewModel.state.collectAsState()
    viewModel.navigator = navigator

    ScreenContainer(paddingValues = CrackDetectTheme.Dimen.NoPadding) {
        CameraContent(
            onPhotoCaptured = viewModel::storePhotoInGallery,
            lastCapturedPhoto = cameraState.capturedImage
        )
    }
}

@Composable
private fun CameraContent(
    onPhotoCaptured: (Bitmap) -> Unit,
    lastCapturedPhoto: Bitmap? = null
) {
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController =
        remember { LifecycleCameraController(context) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = BottomCenter
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    setBackgroundColor(android.graphics.Color.BLACK)
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
        )

        if (lastCapturedPhoto != null) {
            LastPhotoPreview(
                modifier = Modifier.align(alignment = BottomStart),
                lastCapturedPhoto = lastCapturedPhoto
            )
        }

        Buttons.FloatingIcon(
            iconResId = R.drawable.ic_camera,
            onClick = { capturePhoto(context, cameraController, onPhotoCaptured) }
        )
    }
}

private fun capturePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onPhotoCaptured: (Bitmap) -> Unit
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            val correctedBitmap: Bitmap = image
                .toBitmap()
                .rotateImage()

            onPhotoCaptured(correctedBitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
        }
    })
}

fun Bitmap.rotateImage(angle: Float = 90f): Bitmap {
    val matrix = Matrix().apply {
        postRotate(angle)
    }
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}

@Composable
private fun LastPhotoPreview(
    modifier: Modifier = Modifier,
    lastCapturedPhoto: Bitmap
) {

    val capturedPhoto: ImageBitmap =
        remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }

    Card(
        modifier = modifier
            .size(128.dp)
            .padding(16.dp)
    ) {
        Image(
            bitmap = capturedPhoto,
            contentDescription = "Last captured photo",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "PhotoSelectorAnalysisLight"
)
@Composable
fun PhotoSelectorAnalysisLight() {
    PhotoSelectorAnalysisPreview()
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "PhotoSelectorAnalysisDark"
)
@Composable
fun PhotoSelectorAnalysisDark() {
    PhotoSelectorAnalysisPreview()
}

@Composable
private fun PhotoSelectorAnalysisPreview() {
    CrackDetectTheme {
        AnalysisCameraScreen()
    }
}