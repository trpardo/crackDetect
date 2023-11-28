package com.pardo.crackdetect.di

import android.app.Application
import com.pardo.crackdetect.BuildConfig
import com.pardo.crackdetect.CrackDetectApplication
import com.pardo.crackdetect.core.di.ApiKey
import com.pardo.crackdetect.core.di.AppId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun app(app: Application): CrackDetectApplication = app as CrackDetectApplication

    @Singleton
    @Provides
    @ApiKey
    fun apiKey(): String = BuildConfig.API_KEY

    @Singleton
    @Provides
    @AppId
    fun appId(): String = BuildConfig.APPLICATION_ID
}
