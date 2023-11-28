package com.pardo.crackdetect.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

object ViewModelUtils {
    @Composable
    inline fun <reified VM : ViewModel> viewModelFromRoute(
        navController: NavController?,
        navHostRoute: String
    ): VM {
        val entry = try {
            navController?.getBackStackEntry(navHostRoute)
        } catch (e: Exception) {
            null
        }
        val parentEntry = remember { entry }
        return parentEntry?.let {
            if (it.lifecycle.currentState != Lifecycle.State.DESTROYED) {
                hiltViewModel(parentEntry)
            } else {
                hiltViewModel()
            }
        } ?: run {
            hiltViewModel()
        }
    }
}
