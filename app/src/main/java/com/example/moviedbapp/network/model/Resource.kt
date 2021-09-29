package com.example.moviedbapp.network.model

import org.koin.core.logger.MESSAGE
import java.io.Serializable

sealed class Resource<T>{
    data class Success<T>(val data: T, val message: String) : Resource<T>()
    data class ERROR(val message: String): Resource<Nothing>()
    object NO_INTERNET: Resource<Nothing>()
    data class UNKNOWN_ERROR<T>(val data: String): Resource<T>()
}


