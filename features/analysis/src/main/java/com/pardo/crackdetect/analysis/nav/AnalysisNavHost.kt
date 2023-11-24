package com.pardo.crackdetect.analysis.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pardo.crackdetect.analysis.ui.AnalysisCameraScreen
import com.pardo.crackdetect.analysis.ui.AnalysisResultScreen
import com.pardo.crackdetect.analysis.ui.AnalysisSurveyScreen

fun NavGraphBuilder.analysisGraph(
    navigator: AnalysisNavigator,
    navHostRoute: String,
    actionCallBack: () -> Unit = {}
) {
    navigation(
        startDestination = AnalysisDirections.PhotoSelector.route,
        route = AnalysisDirections.Root.route
    ) {
        composable(route = AnalysisDirections.PhotoSelector.route) {
            actionCallBack()
            AnalysisCameraScreen(navigator = navigator)
        }

        composable(route = AnalysisDirections.ExtraInfoSurvey.route) {
            actionCallBack()
            AnalysisSurveyScreen(navigator = navigator)
        }

        composable(route = AnalysisDirections.AnalysisResult.route) {
            actionCallBack()
            AnalysisResultScreen(navigator = navigator)
        }
    }
}
