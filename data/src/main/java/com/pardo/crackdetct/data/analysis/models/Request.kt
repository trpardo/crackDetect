package com.pardo.crackdetct.data.analysis.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class OpenAIRequest(
    val model: String,
    val messages: List<Message>,
    val temperature: Double,
    @SerialName("max_tokens")
    val maxTokens: Int
)

@Serializable
data class Message(
    val role: String,
    val content: JsonElement
)

@Serializable
data class Content(
    val type: String,
    @SerialName("image_url")
    val imageUrl: String
)

fun String.toJsonElement(): JsonElement = JsonPrimitive(this)

fun contentsToJsonElement(contents: Array<Content>): JsonElement = Json.encodeToJsonElement(contents)
