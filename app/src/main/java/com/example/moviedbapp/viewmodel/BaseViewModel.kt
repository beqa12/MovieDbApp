package com.example.moviedbapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedbapp.ApiExceptions
import com.example.moviedbapp.NoInternetException
import com.example.moviedbapp.network.generic.MovieError
import com.example.moviedbapp.network.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

open class BaseViewModel: ViewModel() {

    var progressBar = MutableLiveData<Boolean>()

    fun showLoader(){
        progressBar.postValue(true)
    }

    fun hideLoader(){
        progressBar.postValue(false)
    }

    suspend fun<T> handleError(error: Exception, data: MutableLiveData<Resource<T>>){
        when(error){
            is NoInternetException -> {
                withContext(Dispatchers.Main){
                    data.postValue(Resource(Resource.Status.NO_INTERNET, null, message = error.message))
                }
            }
            is ApiExceptions -> {
                if (error.message == MovieError.SERVER_ERROR){
                    withContext(Dispatchers.Main){
                        data.postValue(Resource(Resource.Status.ERROR, message = error.message))
                    }
                }
            }
            else -> {
                withContext(Dispatchers.Main){
                    data.postValue(Resource(Resource.Status.UNKNOWN_ERROR, message = "Unknown Error"))
                }
            }
        }

    }
}