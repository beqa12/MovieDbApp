package com.example.moviedbapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.moviedbapp.R
import com.example.moviedbapp.network.model.Resource
import com.example.moviedbapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObservers()
    }
    private fun setObservers(){
        moviesViewModel.popularMovies.observe(this, Observer {
            when(it.status){
               Resource.Status.SUCCESS -> {
                   it.data?.forEach {movie ->
                       Log.e("TAG", "${movie.popularMovieId!!}")
                   }
               }
            }
        })
    }
}