package com.pardo.crackdetect.core.network

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val error: com.pardo.crackdetect.core.network.Failure) : Result<Nothing>()
}

suspend fun <T, R> Result<R>.flatMap(fn: suspend (R) -> Result<T>): Result<T> =
    when (this) {
        is Result.Failure -> Result.Failure(error)
        is Result.Success -> fn(value)
    }

fun <T, R> Result<R>.fold(foldFailure: (Failure) -> T, foldSuccess: (R) -> T): T =
    when (this) {
        is Result.Failure -> foldFailure(error)
        is Result.Success -> foldSuccess(value)
    }
