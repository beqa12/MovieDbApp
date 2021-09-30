package com.example.moviedbapp.network.model

import java.io.Serializable

class Resource<T> (
    val status: Status? = null,
    val data: T? = null,
    val message: String? = null
): Serializable {

    sealed class Status {
        object SUCCESS: Status()
        object ERROR: Status()
        object UNKNOWN_ERROR: Status()
        object NO_INTERNET: Status()
    }

}


