package com.example.moviedbapp.domain.model

import com.example.moviedbapp.network.model.MovieDto
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movies(
    var page: Int?,
    var movieResults: List<Movie>
): Serializable

data class Movie(
    var image: String?,
    var popularMovieId: Int?,
    var name: String? ,
    var rating: Double? ,
    var date: String?,
    var description: String?
) : Serializable