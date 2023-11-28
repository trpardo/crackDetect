package com.pardo.crackdetect.analysis.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pardo.crackdetect.analysis.AnalysisViewModel
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.components.Buttons
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

    ScreenContainer(
        topBar = {
            Toolbar.Modal(
                title = stringResource(id = R.string.app_analysis_camera_toolbar),
                onClick = { navigator?.navigateUp() }
            )
        },
        content = {
            CameraContent(
                imageBitmap = viewState.value.form.imageBitmap,
                paddingValues = it
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CrackDetectTheme.Spacing.M)
            ) {
                Buttons.Filled(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.app_analysis_camera_button),
                    onClick = viewModel::startAnalyseImage
                )
            }
        }
    )
}

@Composable
private fun CameraContent(
    imageBitmap: ImageBitmap,
    paddingValues: PaddingValues
) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        bitmap = imageBitmap,
        contentDescription = "Captured image",
        contentScale = ContentScale.FillWidth
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit = { }
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.padding(vertical = 10.dp)
    )
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
        // AnalysisCameraScreen()
    }
}
