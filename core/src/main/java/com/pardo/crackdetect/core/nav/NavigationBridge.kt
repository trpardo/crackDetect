package com.pardo.crackdetect.core.nav

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher

sealed class DestinationActivity(
    @Suppress("unused") val actionFilter: String?,
    var activityType: Class<*>?,
    bundle: Bundle? = null
) : Destination.Activity(bundle = bundle)

object Dashboard : DestinationActivity(null, null)

const val DISPLAYABLE_EXTRA = "displayable_extra"

interface NavigationBridge {
    fun open(
        dest: DestinationActivity,
        resultLauncher: ActivityResultLauncher<Intent>? = null
    )
}

class NavigationBridgeImpl(private val navigationUtils: NavigationUtils) : NavigationBridge {
    override fun open(
        dest: DestinationActivity,
        resultLauncher: ActivityResultLauncher<Intent>?
    ) {
        when (dest) {
            Dashboard -> navigationUtils.startActivityClearingTask(
                dest.activityType!!,
                dest.bundle,
                resultLauncher
            )
        }
    }
}