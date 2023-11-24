package com.pardo.crackdetect.analysis.nav

import androidx.navigation.NavController
import com.pardo.crackdetect.core.nav.BaseNavigator
import com.pardo.crackdetect.core.nav.NavigationBridge

interface AnalysisNavigator : BaseNavigator {
    fun openPhotoSelector(popUpToRoute: String? = null)
    fun openSurvey(popUpToRoute: String? = null)
    fun openResult(popUpToRoute: String? = null)

}

class AnalysisNavigatorImpl(
    override val navigationBridge: NavigationBridge,
    override var navController: NavController? = null
) : AnalysisNavigator {
    override fun openPhotoSelector(popUpToRoute: String?) {
        super.navigate(
            dst = AnalysisDirections.PhotoSelector,
            popUpToRoute = popUpToRoute
        )
    }

    override fun openSurvey(popUpToRoute: String?) {
        super.navigate(
            dst = AnalysisDirections.ExtraInfoSurvey,
            popUpToRoute = popUpToRoute
        )
    }

    override fun openResult(popUpToRoute: String?) {
        super.navigate(
            dst = AnalysisDirections.AnalysisResult,
            popUpToRoute = popUpToRoute
        )
    }
}