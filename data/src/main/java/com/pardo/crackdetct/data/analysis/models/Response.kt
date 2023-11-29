package com.pardo.crackdetct.data.analysis.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenAiResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val usage: Usage,
    val choices: List<Choice>
)

@Serializable
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int,
    @SerialName("total_tokens")
    val totalTokens: Int
)

@Serializable
data class Choice(
    val message: Message,
    @SerialName("finish_details")
    val finishDetails: FinishDetails,
    val index: Int
)

@Serializable
data class FinishDetails(
    val type: String,
    val stop: String
)

@Serializable
data class CrackAnalysisDomainModel(
    val type: String = "",
    val severity: String = "",
    val description: String = ""
)
