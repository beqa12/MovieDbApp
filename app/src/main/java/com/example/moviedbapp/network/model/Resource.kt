package com.example.moviedbapp.network.model

import java.io.Serializable

class Resource<T> (
    val status: Status? = null,
    val data: T? = null,
    val message: String? = null
): Serializable {

    enum class Status {
        SUCCESS, ERROR, UNKNOWN_ERROR, NO_INTERNET
    }

}


