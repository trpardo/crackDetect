package com.pardo.crackdetect.core.network

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val error: com.pardo.crackdetect.core.network.Failure) : Result<Nothing>()
}

fun <T, R> Result<R>.fold(foldFailure: (Failure) -> T, foldSuccess: (R) -> T): T =
    when (this) {
        is Result.Failure -> foldFailure(error)
        is Result.Success -> foldSuccess(value)
    }
