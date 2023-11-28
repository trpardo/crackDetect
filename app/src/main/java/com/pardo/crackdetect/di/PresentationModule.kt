package com.pardo.crackdetect.di

import android.app.Activity
import androidx.activity.ComponentActivity
import com.pardo.crackdetect.core.nav.NavigationBridge
import com.pardo.crackdetect.core.nav.NavigationBridgeImpl
import com.pardo.crackdetect.core.nav.NavigationUtils
import com.pardo.crackdetect.nav.DashboardNavigator
import com.pardo.crackdetect.nav.DashboardNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class PresentationModule {
    @ActivityScoped
    @Provides
    fun getActivity(activity: Activity): ComponentActivity = activity as ComponentActivity

    @ActivityScoped
    @Provides
    fun getNavigationUtils(activity: ComponentActivity): NavigationUtils =
        NavigationUtils(activity)

    @ActivityScoped
    @Provides
    fun getGlobalNavigator(navigationUtils: NavigationUtils): NavigationBridge =
        NavigationBridgeImpl(navigationUtils)

    @ActivityScoped
    @Provides
    fun dashboardNavigator(
        navigationBridge: NavigationBridge
    ): DashboardNavigator = DashboardNavigatorImpl(navigationBridge)
}
