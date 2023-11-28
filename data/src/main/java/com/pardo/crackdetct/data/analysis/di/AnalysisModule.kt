package com.pardo.crackdetct.data.analysis.di

import com.pardo.crackdetct.data.analysis.AnalysisApi
import com.pardo.crackdetct.data.analysis.AnalysisDataSource
import com.pardo.crackdetct.data.analysis.AnalysisDataSourceImpl
import com.pardo.crackdetct.data.analysis.AnalysisUseCase
import com.pardo.crackdetct.data.analysis.AnalysisUseCaseImpl
import com.pardo.crackdetect.core.di.ApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient

@InstallIn(ViewModelComponent::class)
@Module
class AnalysisModule {
    @ViewModelScoped
    @Provides
    fun providesAnalysisApi(
        networkClient: HttpClient,
        @ApiKey apiKey: String
    ): AnalysisApi = AnalysisApi(networkClient, apiKey)

    @ViewModelScoped
    @Provides
    fun providesAnalysisDataSource(
        analysisApi: AnalysisApi
    ): AnalysisDataSource = AnalysisDataSourceImpl(analysisApi)

    @ViewModelScoped
    @Provides
    fun providesAnalysisUseCase(
        analysisDataSource: AnalysisDataSource
    ): AnalysisUseCase = AnalysisUseCaseImpl(analysisDataSource)
}
