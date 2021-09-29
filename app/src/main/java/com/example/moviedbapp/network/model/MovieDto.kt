package com.example.moviedbapp.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDto(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var movieResults: List<MovieResults>?
): Serializable

class MovieResults(
    @SerializedName("backdrop_path")
    var image: String?,
    @SerializedName("id")
    var popularMovieId: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("vote_average")
    var rating: Double?,
    @SerializedName("first_air_date")
    var date: String?,
    @SerializedName("overview")
    var description: String?
) : Serializable