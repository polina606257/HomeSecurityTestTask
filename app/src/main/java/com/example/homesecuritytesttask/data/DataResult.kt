package com.example.homesecuritytesttask.data

sealed class DataResult<out R> {
    data class Success <out T>(val response: T) : DataResult<T>()
    data class Error(val error: String?) : DataResult<Nothing>()

}