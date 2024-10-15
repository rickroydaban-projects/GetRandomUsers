package com.rickaban.android.getrandomusers.app.domain

interface Error

enum class ResultError: Error {
    Generic,
    Connection,
    Unauthorized
}

fun Int.apiError() = when (this) {
    401 -> ResultError.Unauthorized
    else -> ResultError.Connection
}                