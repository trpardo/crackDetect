package com.pardo.crackdetect.analysis.nav

import com.pardo.crackdetect.core.nav.NavDestination

sealed class AnalysisDirections {
    object Root : NavDestination(dst = "analysis_root")

    object Analysis : NavDestination(dst = "analysis")

    object PhotoSelector : NavDestination(dst = "analysis_photo")

    object AnalysisResult : NavDestination(dst = "analysis_result")
}
