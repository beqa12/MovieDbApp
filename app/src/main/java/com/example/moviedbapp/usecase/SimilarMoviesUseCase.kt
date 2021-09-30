package com.example.moviedbapp.usecase

import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.repository.MovieRepositoryImpl

class SimilarMoviesUseCase(private val repository: MovieRepositoryImpl) {

    suspend fun execute(tvId: Int): List<Movie>{
        val movieDto = repository.getSimilarMovies(tvId)
        val movies = ArrayList<Movie>()
        movieDto.movieResults?.forEach { movieResults ->
            val movie = Movie(
                image = movieResults.image,
                popularMovieId = movieResults.popularMovieId,
                name = movieResults.name,
                rating = movieResults.rating,
                date = movieResults.date,
                description = movieResults.description
            )
            movies.add(movie)
        }
        return movies
    }
}