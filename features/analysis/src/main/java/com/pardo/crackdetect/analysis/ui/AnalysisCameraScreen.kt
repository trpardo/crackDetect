package com.pardo.crackdetect.analysis.ui

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pardo.crackdetect.analysis.AnalysisViewModel
import com.pardo.crackdetect.analysis.AnalysisViewState
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.components.Buttons
import com.pardo.crackdetect.components.ErrorDialog
import com.pardo.crackdetect.components.ImageCaptured
import com.pardo.crackdetect.components.ProgressBar
import com.pardo.crackdetect.components.ScreenContainer
import com.pardo.crackdetect.components.Toolbar
import com.pardo.crackdetect.theme.CrackDetectTheme
import com.pardo.crackdetect.theme.R

@ExperimentalPermissionsApi
@Composable
fun AnalysisCameraScreen(
    viewModel: AnalysisViewModel = hiltViewModel(),
    navigator: AnalysisNavigator? = null
) {
    val viewState = viewModel.viewState.collectAsState()
    val showCamera = remember { mutableStateOf(true) }

    ScreenContainer(
        topBar = {
            Toolbar.Modal(
                title = stringResource(id = R.string.app_analysis_camera_toolbar),
                onClick = { navigator?.navigateUp() }
            )
        },
        content = {
            when (viewState.value) {
                is AnalysisViewState.Error -> ErrorDialog(onClick = viewModel::closeErrorDialog)
                is AnalysisViewState.Loading -> ProgressBar()
                is AnalysisViewState.ResultSuccess -> navigator?.openResult()
                else -> {}
            }

            if (showCamera.value) {
                CameraScreen(onTakePhoto = { bitmap ->
                    viewModel.onTakePhoto(bitmap)
                    showCamera.value = false
                })
            } else {
                ImageCaptured(
                    image = viewState.value.form.image.asImageBitmap(),
                    paddingValues = it
                )
            }
        },
        bottomBar = {
            if (!showCamera.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CrackDetectTheme.Spacing.M)
                ) {
                    Buttons.Filled(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.app_analysis_camera_button),
                        onClick = viewModel::analyseImage,
                        enabled = !viewState.value.isLoading
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(
    onTakePhoto: (image: Bitmap) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember { LifecycleCameraController(context) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                content = { Text(text = "Take photo") },
                onClick = {
                    val mainExecutor = ContextCompat.getMainExecutor(context)
                    cameraController.takePicture(
                        mainExecutor,
                        object : ImageCapture.OnImageCapturedCallback() {
                            override fun onCaptureSuccess(image: ImageProxy) {
                                val matrix = Matrix().apply {
                                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                                }
                                val rotatedBitmap = Bitmap.createBitmap(
                                    image.toBitmap(),
                                    0,
                                    0,
                                    image.width,
                                    image.height,
                                    matrix,
                                    true
                                )
                                image.close()
                                onTakePhoto.invoke(rotatedBitmap)
                            }

                            override fun onError(exception: ImageCaptureException) {
                                super.onError(exception)
                                Log.e("Camera", "Couldn't take photo: ", exception)
                            }
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PhotoSelectorAnalysisPreview() {
    CrackDetectTheme {
        AnalysisCameraScreen()
    }
}
