package com.pardo.crackdetect.analysis.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator

@Composable
fun AnalysisSurveyScreen(
    navigator: AnalysisNavigator?
) {
    Text("Survey screen")
    Button(onClick = { navigator?.openResult() }) {
        Text("Go to next screen")
    }
}