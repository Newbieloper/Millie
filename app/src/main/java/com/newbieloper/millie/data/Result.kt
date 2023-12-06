package com.newbieloper.millie.data

import com.newbieloper.millie.data.remote.ErrorBody

sealed class Result<out R> {

    data class Success<T>(val data: T) : Result<T>()

    data class ErrorResponse(val errorBody: ErrorBody) : Result<Nothing>()

    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is ErrorResponse -> "ErrorResponse[error=$errorBody]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

fun <T> Result<T>.successOr(fallback: T): T {
    return data ?: fallback
}