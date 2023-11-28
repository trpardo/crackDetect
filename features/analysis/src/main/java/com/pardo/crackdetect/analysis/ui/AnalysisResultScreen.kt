package com.pardo.crackdetect.analysis.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.pardo.crackdetect.analysis.AnalysisViewModel

@Composable
fun AnalysisResultScreen(
    viewModel: AnalysisViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.analyseImage()
    }

    /*when(viewState.value) {
        is AnalysisViewState.AnalyseImage -> viewModel.analyseImage()
        is AnalysisViewState.Error -> Text("Error")
        is AnalysisViewState.Loading -> Text("Loading")
        is AnalysisViewState.ResultSuccess -> Text(viewState.value.form.type)
        else -> {}
    }*/
}
