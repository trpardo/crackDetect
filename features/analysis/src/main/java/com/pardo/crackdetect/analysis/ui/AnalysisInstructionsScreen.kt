package com.pardo.crackdetect.analysis.ui

import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.pardo.crackdetect.analysis.AnalysisViewModel
import com.pardo.crackdetect.analysis.AnalysisViewState
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.components.Buttons
import com.pardo.crackdetect.components.ScreenContainer
import com.pardo.crackdetect.components.StepItem
import com.pardo.crackdetect.components.StepsList
import com.pardo.crackdetect.components.Titles
import com.pardo.crackdetect.components.Toolbar
import com.pardo.crackdetect.theme.CrackDetectTheme
import com.pardo.crackdetect.theme.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AnalysisInstructionsScreen(
    viewModel: AnalysisViewModel = hiltViewModel(),
    navigator: AnalysisNavigator? = null
) {
    val cameraPermission = cameraPermissionState()
    viewModel.navigator = navigator

    val viewState = viewModel.viewState.collectAsState()

    val launcherForImageCapture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = viewModel::saveImage
    )

    ScreenContainer(
        topBar = {
            Toolbar.Nav(
                title = stringResource(id = R.string.app_analysis_instructions_toolbar),
                onClick = { navigator?.navigateUp() }
            )
        },
        content = {
            when (viewState.value) {
                AnalysisViewState.Initial -> InstructionsContent(paddingValues = it)
                is AnalysisViewState.CaptureImage -> navigator?.openPhotoSelector()
                else -> {}
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CrackDetectTheme.Spacing.M)
            ) {
                Buttons.Filled(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.app_analysis_instructions_button),
                    onClick = {
                        when (cameraPermission.status) {
                            is PermissionStatus.Denied -> cameraPermission.launchPermissionRequest()
                            PermissionStatus.Granted -> launcherForImageCapture.launch()
                        }
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun cameraPermissionState(): PermissionState {
    return rememberPermissionState(permission = android.Manifest.permission.CAMERA)
}

@Composable
private fun InstructionsContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(CrackDetectTheme.Spacing.L)
    ) {
        Titles.ExtraTitle(text = stringResource(id = R.string.app_analysis_instructions_title))
        StepsList(
            modifier = Modifier.fillMaxWidth(),
            items = listOf(
                StepItem(
                    title = stringResource(id = R.string.app_analysis_instructions_step1_title),
                    description = stringResource(id = R.string.app_analysis_instructions_step1_description)
                ),
                StepItem(
                    title = stringResource(id = R.string.app_analysis_instructions_step2_title),
                    description = stringResource(id = R.string.app_analysis_instructions_step2_description)
                ),
                StepItem(
                    title = stringResource(id = R.string.app_analysis_instructions_step3_title),
                    description = stringResource(id = R.string.app_analysis_instructions_step3_description)
                )
            )
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "InstructionsAnalysisLight"
)
@Composable
fun InstructionsAnalysisLight() {
    InstructionsAnalysisPreview()
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "InstructionsAnalysisDark"
)
@Composable
fun InstructionsAnalysisDark() {
    InstructionsAnalysisPreview()
}

@Composable
private fun InstructionsAnalysisPreview() {
    CrackDetectTheme {
        AnalysisInstructionsScreen()
    }
}
