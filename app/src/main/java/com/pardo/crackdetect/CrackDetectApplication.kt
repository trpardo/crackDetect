package com.pardo.crackdetect

import android.app.Application
import com.pardo.crackdetect.core.nav.Dashboard
import com.pardo.crackdetect.ui.DashboardActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CrackDetectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        this.setUpNavigation()
    }

    private fun setUpNavigation() {
        Dashboard.activityType = DashboardActivity::class.java
    }
}