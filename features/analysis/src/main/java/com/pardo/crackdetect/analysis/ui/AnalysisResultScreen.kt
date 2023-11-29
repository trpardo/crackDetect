package com.pardo.crackdetect.analysis.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pardo.crackdetect.analysis.AnalysisViewModel
import com.pardo.crackdetect.analysis.AnalysisViewState
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.components.DescriptionRow
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
        verticalArrangement = Arrangement.spacedBy(CrackDetectTheme.Spacing.S)
    ) {
        Column {
            Titles.ExtraTitle(text = stringResource(id = R.string.app_analysis_result_title))
            Titles.Label(text = stringResource(id = R.string.app_analysis_result_disclaimer))
        }

        Column {
            DescriptionRow(
                title = stringResource(id = R.string.app_analysis_result_type),
                description = viewState.form.analysis.type
            )
            DescriptionRow(
                title = stringResource(id = R.string.app_analysis_result_severity),
                description = viewState.form.analysis.severity
            )
        }
        Box(
            modifier = Modifier
                .size(CrackDetectTheme.Dimen.ImageCircularSize)
                .padding(CrackDetectTheme.Dimen.ImageCircularPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                bitmap = viewState.form.image.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Column {
            Titles.SimpleTitle(text = stringResource(id = R.string.app_analysis_result_description))
            Titles.SimpleDescription(text = viewState.form.analysis.description)
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
