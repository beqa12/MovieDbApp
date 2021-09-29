package com.example.moviedbapp.usecase

import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.repository.MovieRepositoryImpl

class PopularMoviesUseCase(private val repository: MovieRepositoryImpl) {

    suspend fun execute(page: Int): List<Movie>{
        val movieDto = repository.getPopularMovies(page)
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