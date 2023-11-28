package com.pardo.crackdetect.nav

import androidx.navigation.NavController
import com.pardo.crackdetect.analysis.nav.AnalysisDirections
import com.pardo.crackdetect.core.nav.BaseNavigator
import com.pardo.crackdetect.core.nav.NavigationBridge

interface DashboardNavigator : BaseNavigator {
    fun startAnalysis(popUpToRoute: String? = null)
}

class DashboardNavigatorImpl(
    override val navigationBridge: NavigationBridge,
    override var navController: NavController? = null
) : DashboardNavigator {
    override fun startAnalysis(popUpToRoute: String?) {
        super.navigate(
            dst = AnalysisDirections.Analysis,
            popUpToRoute = popUpToRoute
        )
    }
}
