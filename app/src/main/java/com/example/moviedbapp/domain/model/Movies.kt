package com.example.moviedbapp.domain.model

import java.io.Serializable

data class Movie(
    var image: String?,
    var popularMovieId: Int?,
    var name: String? ,
    var rating: Double? ,
    var date: String?,
    var description: String?
) : Serializable