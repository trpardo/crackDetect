package com.pardo.crackdetect.ui

import android.content.Intent
import android.os.Bundle
import androidx.navigation.compose.rememberNavController
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.core.di.BaseActivity
import com.pardo.crackdetect.core.di.content
import com.pardo.crackdetect.nav.DashboardNavHost
import com.pardo.crackdetect.nav.DashboardNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {
    @Inject
    lateinit var dashBoardNavigator: DashboardNavigator

    @Inject
    lateinit var analysisNavigator: AnalysisNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        content(activity = this) {
            DashboardNavHost(
                navController = rememberNavController(),
                dashboardNavigator = dashBoardNavigator,
                analysisNavigator = analysisNavigator
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.startActivity(intent)
    }
}
