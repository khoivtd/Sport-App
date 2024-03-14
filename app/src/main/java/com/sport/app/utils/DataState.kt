package com.sport.app.utils

sealed class DataState<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?): DataState<T>(data)
    class Error<T>(errorMessage: String?, data: T? = null): DataState<T>(data, errorMessage)
    class Loading<T>(data: T? = null): DataState<T>(data)
}