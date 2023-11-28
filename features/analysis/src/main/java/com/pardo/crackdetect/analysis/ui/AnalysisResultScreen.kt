package com.pardo.crackdetect.analysis.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pardo.crackdetect.analysis.AnalysisViewModel
import com.pardo.crackdetect.analysis.AnalysisViewState
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.components.DescriptionRow
import com.pardo.crackdetect.components.ImageCaptured
import com.pardo.crackdetect.components.ScreenContainer
import com.pardo.crackdetect.components.Titles
import com.pardo.crackdetect.components.Toolbar
import com.pardo.crackdetect.theme.CrackDetectTheme
import com.pardo.crackdetect.theme.R

@Composable
fun AnalysisResultScreen(
    viewModel: AnalysisViewModel = hiltViewModel(),
    navigator: AnalysisNavigator? = null
) {
    val viewState = viewModel.viewState.collectAsState()

    ScreenContainer(
        topBar = {
            Toolbar.Nav(
                title = stringResource(id = R.string.app_analysis_result_toolbar),
                onClick = { navigator?.navigateUp() }
            )
        },
        content = {
            ResultContent(
                paddingValues = it,
                viewState = viewState.value
            )
        }
    )
}

@Composable
private fun ResultContent(
    paddingValues: PaddingValues,
    viewState: AnalysisViewState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(CrackDetectTheme.Spacing.L)
    ) {
        Column {
            Titles.ExtraTitle(text = stringResource(id = R.string.app_analysis_result_title))
            Titles.Label(text = stringResource(id = R.string.app_analysis_result_disclaimer))
        }

        Column {
            DescriptionRow(
                title = stringResource(id = R.string.app_analysis_result_type),
                description = viewState.form.type
            )
            DescriptionRow(
                title = stringResource(id = R.string.app_analysis_result_severity),
                description = viewState.form.severity
            )
        }

        ImageCaptured(
            image = viewState.form.image,
            paddingValues = paddingValues
        )

        Column {
            Titles.SimpleTitle(text = stringResource(id = R.string.app_analysis_result_description))
            Titles.SimpleDescription(text = viewState.form.description)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "ResultAnalysisLight"
)
@Composable
fun ResultAnalysisLight() {
    ResultAnalysisPreview()
}

@Preview(
    showBackground = true,
    widthDp = 420,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "ResultAnalysisDark"
)
@Composable
fun ResultAnalysisDark() {
    ResultAnalysisPreview()
}

@Composable
private fun ResultAnalysisPreview() {
    CrackDetectTheme {
        AnalysisResultScreen()
    }
}
