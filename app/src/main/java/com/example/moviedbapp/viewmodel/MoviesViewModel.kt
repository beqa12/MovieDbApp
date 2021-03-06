package com.example.moviedbapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.network.model.Resource
import com.example.moviedbapp.usecase.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesViewModel(private val baseUseCase: BaseUseCase) : BaseViewModel() {

    private var _popularMovies = MutableLiveData<Resource<List<Movie>>>()
    val popularMovies: LiveData<Resource<List<Movie>>> get() = _popularMovies

    private var _similarMovies = MutableLiveData<Resource<List<Movie>>>()
    val similarMovies: LiveData<Resource<List<Movie>>> get() = _similarMovies

    fun getPopularMovies(page: Int) {
        showLoader()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = baseUseCase.popularMoviesUseCase.execute(page)
                withContext(Dispatchers.Main) {
                    hideLoader()
                    _popularMovies.value = Resource(Resource.Status.SUCCESS, response, "Success")
                }
            } catch (e: Exception) {
                hideLoader()
                handleError(e, _popularMovies)
            }
        }
    }

    fun getSimilarMovies(tvId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = baseUseCase.similarMoviesUseCase.execute(tvId)
                withContext(Dispatchers.Main) {
                    _similarMovies.value = Resource(Resource.Status.SUCCESS, response, "Success")
                }
            } catch (e: Exception) {
                handleError(e, _similarMovies)
            }
        }
    }
}