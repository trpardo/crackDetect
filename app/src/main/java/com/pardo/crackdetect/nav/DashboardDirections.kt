package com.pardo.crackdetect.nav

import com.pardo.crackdetect.core.nav.NavDestination

sealed class DashboardDirections {
    object Root : NavDestination(dst = "dashboard_root")
    object Initial : NavDestination(dst = "dashboard")
}
