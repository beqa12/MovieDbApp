package com.example.moviedbapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.network.model.Resource
import com.example.moviedbapp.usecase.PopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesViewModel(private val popularMoviesUseCase: PopularMoviesUseCase) : BaseViewModel() {

    private var _popularMovies = MutableLiveData<Resource<List<Movie>>>()
    val popularMovies: LiveData<Resource<List<Movie>>> get() = _popularMovies

    init {
        getPopularMovies(1)
    }
    fun getPopularMovies(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popularMoviesUseCase.execute(page)
                withContext(Dispatchers.Main) {
                    _popularMovies.value = Resource(Resource.Status.SUCCESS, response, "Success")
                }
            } catch (e: Exception) {
                handleError(e, _popularMovies)
            }
        }
    }
}