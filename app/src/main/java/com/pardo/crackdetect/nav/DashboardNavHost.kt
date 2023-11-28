package com.pardo.crackdetect.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pardo.crackdetect.analysis.nav.AnalysisDirections
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.analysis.nav.analysisGraph
import com.pardo.crackdetect.analysis.ui.AnalysisInstructionsScreen
import com.pardo.crackdetect.components.ScreenContainer
import com.pardo.crackdetect.core.utils.ViewModelUtils
import com.pardo.crackdetect.ui.WelcomeScreen

@Composable
fun DashboardNavHost(
    navController: NavHostController,
    dashboardNavigator: DashboardNavigator,
    analysisNavigator: AnalysisNavigator
) {
    dashboardNavigator.navController = navController
    analysisNavigator.navController = navController

    ScreenContainer {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = DashboardDirections.Initial.route,
            route = DashboardDirections.Root.route
        ) {
            composable(route = DashboardDirections.Initial.route) {
                WelcomeScreen(navigator = dashboardNavigator)
            }

            composable(route = AnalysisDirections.Analysis.route) {
                AnalysisInstructionsScreen(
                    viewModel = ViewModelUtils.viewModelFromRoute(
                        navController = navController,
                        navHostRoute = AnalysisDirections.Analysis.route
                    ),
                    navigator = analysisNavigator
                )
            }

            analysisGraph(
                navigator = analysisNavigator,
                navHostRoute = AnalysisDirections.Analysis.route
            )
        }
    }
}
