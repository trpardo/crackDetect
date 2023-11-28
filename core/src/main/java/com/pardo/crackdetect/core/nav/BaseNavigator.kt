package com.pardo.crackdetect.core.nav

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

interface BaseNavigator {
    val navigationBridge: NavigationBridge
    var navController: NavController?

    companion object {
        private val TAG = BaseNavigator::class.simpleName
    }

    fun navigate(
        dst: Destination,
        popUpToRoute: String? = null,
        vararg args: Pair<String, *>
    ) {
        when (dst) {
            is Destination.Activity -> this.openActivity(dst as DestinationActivity)
            is Destination.NavComponent -> {
                this.navigatePopUpToWithArgs(
                    route = (dst as NavDestination).createNavRoute(*args),
                    popUpToRoute = popUpToRoute
                )
            }
        }
    }

    private fun navigatePopUpToWithArgs(
        route: String,
        popUpToRoute: String? = null
    ) {
        this.navigate(route) {
            launchSingleTop = true
            popUpToRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }

    private fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = {}
    ) {
        try {
            navController?.navigate(route, builder)
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    fun navigateUp() {
        navController?.navigateUp()
    }

    fun openActivity(destinationActivity: DestinationActivity) {
        navigationBridge.open(destinationActivity)
    }
}
