package com.example.moviedbapp.network.generic

import com.example.moviedbapp.ApiExceptions
import com.example.moviedbapp.network.generic.MovieError.SERVER_ERROR
import com.example.moviedbapp.network.generic.MovieError.UNKNOWN_ERROR
import retrofit2.Response

abstract class GenericRequest {
    suspend fun <T> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        var error = ""
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            error = when {
                response.code() == 500 -> {
                    SERVER_ERROR
                }
                else -> {
                    UNKNOWN_ERROR
                }
            }
        }
        throw ApiExceptions(error)
    }
}