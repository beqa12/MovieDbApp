package com.example.moviedbapp.usecase

class BaseUseCase(val popularMoviesUseCase: PopularMoviesUseCase,
                  val similarMoviesUseCase: SimilarMoviesUseCase)