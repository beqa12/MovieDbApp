package com.example.moviedbapp.network.retrofit

import com.example.moviedbapp.network.endpoints.Endpoints
import com.example.moviedbapp.network.interceptors.DefaultParameterInterceptor
import com.example.moviedbapp.network.interceptors.NoInternetInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetrofit(defaultParameterInterceptor: DefaultParameterInterceptor,noInternetInterceptor: NoInternetInterceptor) {

    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(defaultParameterInterceptor)
        .addInterceptor(noInternetInterceptor)
        .build()

    private var retrofitInstance: Retrofit? = null

    fun getRetrofitInstance(): Retrofit {
        synchronized(this) {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(Endpoints.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofitInstance!!
        }
    }
}