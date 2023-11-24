package com.pardo.crackdetect.analysis.di

import android.content.Context
import com.pardo.crackdetect.analysis.SavePhotoToGalleryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class AnalysisModule {
    @ViewModelScoped
    @Provides
    fun providesCameraUseCase(
        @ApplicationContext context: Context
    ): SavePhotoToGalleryUseCase = SavePhotoToGalleryUseCase(context)
}