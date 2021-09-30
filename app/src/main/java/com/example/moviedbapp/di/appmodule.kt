package com.example.moviedbapp.di

import com.example.moviedbapp.repository.MovieRepositoryImpl
import com.example.moviedbapp.usecase.BaseUseCase
import com.example.moviedbapp.usecase.PopularMoviesUseCase
import com.example.moviedbapp.usecase.SimilarMoviesUseCase
import com.example.moviedbapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        SimilarMoviesUseCase(get())
    }

    single {
        PopularMoviesUseCase(get())
    }

    single {
        BaseUseCase(get(), get())
    }

    single {
        MovieRepositoryImpl(get())
    }

    viewModel {
        MoviesViewModel(get())
    }
}