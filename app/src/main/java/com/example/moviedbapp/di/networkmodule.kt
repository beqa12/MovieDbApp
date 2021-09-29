package com.example.moviedbapp.di

import com.example.moviedbapp.network.api.MoviesApi
import com.example.moviedbapp.network.interceptors.DefaultParameterInterceptor
import com.example.moviedbapp.network.interceptors.NoInternetInterceptor
import com.example.moviedbapp.network.retrofit.MovieRetrofit
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {

    single {
        DefaultParameterInterceptor()
    }

    single {
        NoInternetInterceptor(get())
    }

    single {
        MovieRetrofit(get(), get())
    }

    single {
        return@single get<MovieRetrofit>().getRetrofitInstance().create(MoviesApi::class.java)
    }
}