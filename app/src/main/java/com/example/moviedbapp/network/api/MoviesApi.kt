package com.example.moviedbapp.network.api

import com.example.moviedbapp.network.endpoints.Endpoints
import com.example.moviedbapp.network.model.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(Endpoints.POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<MovieDto>

    @GET(Endpoints.SIMILAR_MOVIES)
    suspend fun getSimilarMovies(
        @Path("tv_id") id: Int
    ): Response<MovieDto>
}