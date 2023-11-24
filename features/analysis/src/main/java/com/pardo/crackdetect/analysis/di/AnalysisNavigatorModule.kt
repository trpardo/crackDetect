package com.pardo.crackdetect.analysis.di

import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.analysis.nav.AnalysisNavigatorImpl
import com.pardo.crackdetect.core.nav.NavigationBridge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class AnalysisNavigatorModule {
    @ActivityScoped
    @Provides
    fun analysisNavigator(
        navigationBridge: NavigationBridge
    ): AnalysisNavigator = AnalysisNavigatorImpl(navigationBridge)
}