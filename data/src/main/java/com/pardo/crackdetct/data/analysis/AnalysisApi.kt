package com.pardo.crackdetct.data.analysis

import com.pardo.crackdetct.data.GPTModel
import com.pardo.crackdetct.data.analysis.models.Message
import com.pardo.crackdetct.data.analysis.models.OpenAIRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class AnalysisApi(
    private val client: HttpClient,
    private val apiKey: String
) {
    companion object {
        const val PATH: String = "https://api.openai.com/v1/chat/completions"
    }

    suspend fun analyseImage(messages: List<Message>) =
        client.post(PATH) {
            this.header("Authorization", "Bearer $apiKey")
            this.setBody(
                Json.encodeToJsonElement(
                    serializer = serializer(),
                    value = OpenAIRequest(
                        model = GPTModel.gpt4_turbo_with_vision,
                        messages = messages,
                        temperature = 0.2,
                        maxTokens = 4096
                    )
                )
            )
        }
}
