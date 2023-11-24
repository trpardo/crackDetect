package com.pardo.crackdetect.analysis.nav

import com.pardo.crackdetect.core.nav.NavDestination


sealed class AnalysisDirections {
    object Root : NavDestination(dst = "analysis_root")

    object PhotoSelector: NavDestination(dst = "analysis_photo")

    object ExtraInfoSurvey : NavDestination(dst = "analysis_survey")

    object AnalysisResult: NavDestination(dst = "analysis_result")
}
