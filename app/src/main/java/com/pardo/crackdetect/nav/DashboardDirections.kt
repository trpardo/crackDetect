package com.pardo.crackdetect.nav

import com.pardo.crackdetect.core.nav.NavDestination

sealed class DashBoardDirections {
    object Root : NavDestination(dst = "dashboard_root")
    object Initial : NavDestination(dst = "dashboard")
    object Analysis : NavDestination(dst = "analysis")
}