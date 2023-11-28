package com.pardo.crackdetct.data.analysis

import com.pardo.crackdetct.data.analysis.models.Content
import com.pardo.crackdetct.data.analysis.models.CrackAnalysisDomainModel
import com.pardo.crackdetct.data.analysis.models.Message
import com.pardo.crackdetct.data.analysis.models.OpenAiResponse
import com.pardo.crackdetct.data.analysis.models.contentsToJsonElement
import com.pardo.crackdetct.data.analysis.models.systemPrompt
import com.pardo.crackdetct.data.analysis.models.toJsonElement
import com.pardo.crackdetect.core.network.Result
import com.pardo.crackdetect.core.network.errorHandler
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json

interface AnalysisDataSource {
    suspend fun analyseImage(imageBase64: String): Result<CrackAnalysisDomainModel>
}

class AnalysisDataSourceImpl(
    private val analysisApi: AnalysisApi
) : AnalysisDataSource {
    override suspend fun analyseImage(imageBase64: String): Result<CrackAnalysisDomainModel> {
        return try {
            val response = analysisApi.analyseImage(mapRequest(imageBase64))
            Result.Success(mapResponse(response))
        } catch (e: Exception) {
            Result.Failure(e.errorHandler())
        }
    }

    private fun mapRequest(image: String) = listOf(
        Message(
            role = "system",
            content = systemPrompt().toJsonElement()
        ),
        Message(
            role = "user",
            content = contentsToJsonElement(
                arrayOf(
                    Content(
                        type = "image_url",
                        imageUrl = "data:image/png;base64,$image"
                    )
                )
            )
        )
    )

    private suspend fun mapResponse(response: HttpResponse): CrackAnalysisDomainModel {
        val decodedResponse = Json.decodeFromString<OpenAiResponse>(response.body())
        return CrackAnalysisDomainModel(
            type = decodedResponse.choices.first().message.toString(),
            severity = "",
            description = ""
        )
    }
}
