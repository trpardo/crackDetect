package com.pardo.crackdetct.data.analysis

import com.pardo.crackdetct.data.analysis.models.CrackAnalysisDomainModel
import com.pardo.crackdetect.core.network.Result

interface AnalysisUseCase {
    suspend fun analyseImage(imageBase64: String): Result<CrackAnalysisDomainModel>
}

class AnalysisUseCaseImpl(
    private val analysisDataSource: AnalysisDataSource
) : AnalysisUseCase {
    override suspend fun analyseImage(imageBase64: String): Result<CrackAnalysisDomainModel> =
        analysisDataSource.analyseImage(imageBase64 = imageBase64)
}
