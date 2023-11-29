package com.pardo.crackdetect.core.network

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import java.net.HttpURLConnection

fun Exception.errorHandler() = when (this) {
    is ServerResponseException -> Failure.InternalError
    is ClientRequestException ->
        when (this.response.status.value) {
            HttpURLConnection.HTTP_BAD_REQUEST -> Failure.BadRequest
            HttpURLConnection.HTTP_UNAUTHORIZED -> Failure.Unauthorized
            HttpURLConnection.HTTP_FORBIDDEN -> Failure.Forbidden
            HttpURLConnection.HTTP_NOT_FOUND -> Failure.NotFound
            else -> Failure.HttpError(this)
        }
    is RedirectResponseException -> Failure.HttpError(this)
    is SerializationException -> Failure.DataError(this)
    is IOException -> Failure.NetworkError(this)
    else -> Failure.GenericError(this)
}

sealed class Failure(val cause: String? = null) {
    object InternalError : Failure(InternalError::class.java.simpleName)
    object BadRequest : Failure(BadRequest::class.java.simpleName)
    object Unauthorized : Failure(Unauthorized::class.java.simpleName)
    object Forbidden : Failure(Forbidden::class.java.simpleName)
    object NotFound : Failure(NotFound::class.java.simpleName)

    class HttpError(e: Exception) : Failure(e.cause?.message)
    class DataError(e: Exception) : Failure(e.cause?.message)
    class NetworkError(e: Exception) : Failure(e.cause?.message)
    class GenericError(e: Exception) : Failure(e.cause?.message)
}
