package com.example.moviedbapp.repository

import com.example.moviedbapp.network.model.MovieDto

interface MovieRepository {

    suspend fun getPopularMovies(page: Int): MovieDto

    suspend fun getSimilarMovies(tvId: Int): MovieDto

}