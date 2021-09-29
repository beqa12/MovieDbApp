package com.example.moviedbapp.repository

import com.example.moviedbapp.network.api.MoviesApi
import com.example.moviedbapp.network.generic.GenericRequest
import com.example.moviedbapp.network.model.MovieDto

class MovieRepositoryImpl(private val apiService: MoviesApi): MovieRepository, GenericRequest() {

    override suspend fun getPopularMovies(page: Int): MovieDto {
        return apiRequest { apiService.getPopularMovies(page) }
    }

    override suspend fun getSimilarMovies(tvId: Int): MovieDto {
        return apiRequest {apiService.getSimilarMovies(tvId)}
    }
}