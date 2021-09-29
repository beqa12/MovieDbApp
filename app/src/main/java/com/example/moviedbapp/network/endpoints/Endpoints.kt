package com.example.moviedbapp.network.endpoints

object Endpoints {
    const val BASE_URL = "https://api.themoviedb.org"
    const val POPULAR_MOVIES = "/3/tv/popular"
    const val SIMILAR_MOVIES = "/3/tv/{tv_id}/similar"
}