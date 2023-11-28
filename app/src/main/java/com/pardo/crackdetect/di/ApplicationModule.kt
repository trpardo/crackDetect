package com.pardo.crackdetect.di

import android.app.Application
import com.pardo.crackdetect.CrackDetectApplication
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
}
