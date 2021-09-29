package com.example.moviedbapp

import android.app.Application
import com.example.moviedbapp.di.appModule
import com.example.moviedbapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(listOf(networkModule, appModule))
        }
    }
}