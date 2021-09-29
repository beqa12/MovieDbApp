package com.example.moviedbapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedbapp.ApiExceptions
import com.example.moviedbapp.NoInternetException
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.network.generic.MovieError
import com.example.moviedbapp.network.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

open class BaseViewModel: ViewModel() {

    suspend fun<T> handleError(error: Exception, data: MutableLiveData<Resource<T>>){
        when(error){
            is NoInternetException -> {
                withContext(Dispatchers.Main){
                    data.postValue(Resource(Resource.Status.NO_INTERNET))
                }
            }
            is ApiExceptions -> {
                if (error.message == MovieError.SERVER_ERROR){
                    withContext(Dispatchers.Main){
                        data.postValue(Resource(Resource.Status.ERROR))
                    }
                }
            }
            else -> {
                withContext(Dispatchers.Main){
                    withContext(Dispatchers.Main){
                        data.postValue(Resource(Resource.Status.UNKNOWN_ERROR))
                    }
                }
            }
        }

    }
}