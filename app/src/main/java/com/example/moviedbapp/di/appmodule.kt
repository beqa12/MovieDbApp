package com.example.moviedbapp.di

import com.example.moviedbapp.repository.MovieRepositoryImpl
import com.example.moviedbapp.usecase.PopularMoviesUseCase
import com.example.moviedbapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        MovieRepositoryImpl(get())
    }
    single {
        PopularMoviesUseCase(get())
    }

    viewModel {
        MoviesViewModel(get())
    }
}