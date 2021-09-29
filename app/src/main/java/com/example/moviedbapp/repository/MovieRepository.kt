package com.example.moviedbapp.repository

import com.example.moviedbapp.network.model.MovieDto

interface MovieRepository {
    fun getPopularMovies(): MovieDto
}