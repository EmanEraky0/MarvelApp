package com.example.marvelapp.utils

sealed class UiResult<out R> {

    data object Loading : UiResult<Nothing>()

    data class Success<out T>(val data: T?) : UiResult<T>()

    data class Error(val exception: String? = null) : UiResult<Nothing>()
}