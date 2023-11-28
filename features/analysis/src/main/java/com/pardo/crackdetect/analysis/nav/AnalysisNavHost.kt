package com.pardo.crackdetect.analysis.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pardo.crackdetect.analysis.ui.AnalysisCameraScreen
import com.pardo.crackdetect.analysis.ui.AnalysisResultScreen
import com.pardo.crackdetect.core.utils.ViewModelUtils

@OptIn(ExperimentalPermissionsApi::class)
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
            AnalysisCameraScreen(
                viewModel = ViewModelUtils.viewModelFromRoute(
                    navController = navigator.navController,
                    navHostRoute = navHostRoute
                ),
                navigator = navigator
            )
        }

        composable(route = AnalysisDirections.AnalysisResult.route) {
            actionCallBack()
            AnalysisResultScreen(
                viewModel = ViewModelUtils.viewModelFromRoute(
                    navController = navigator.navController,
                    navHostRoute = navHostRoute
                )
            )
        }
    }
}
