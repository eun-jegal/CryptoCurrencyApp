package com.example.cryptocurrencyapp.common

// Put any object in this class
// Easily represent network response and define operations following the state of the response
// - Success -> Display data in a list
// - Error -> Display an error message and show a retry button
// - Loading -> Show a progress bar
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
