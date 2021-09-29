package com.example.moviedbapp.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.moviedbapp.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NoInternetInterceptor(private val context: Context): Interceptor {
    private var appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()){
            throw NoInternetException("Make sure you have internet")
        }else {
            return chain.proceed(chain.request())
        }
    }

    private fun isNetworkAvailable(): Boolean{
        var result = false
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val capabilities =
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        connectivityManager?.let {
            if (capabilities != null) {
                result = when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        true
                    }else -> {
                        false
                    }
                }
            }
        }
        return result
    }
}