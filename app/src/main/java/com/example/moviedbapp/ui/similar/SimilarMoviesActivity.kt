package com.example.moviedbapp.ui.similar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.ActivitySimilarMoviesBinding
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.load
import com.example.moviedbapp.network.model.Resource
import com.example.moviedbapp.toast
import com.example.moviedbapp.utils.POPULAR_MOVIE_MODEL_KEY
import com.example.moviedbapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimilarMoviesActivity : AppCompatActivity() {

    val moviesViewModel: MoviesViewModel by viewModel()
    private var _binding: ActivitySimilarMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var similarMoviesAdapter: SimilarMoviesAdapter
    private lateinit var movieModel: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySimilarMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
        movieModel = intent.getSerializableExtra(POPULAR_MOVIE_MODEL_KEY) as Movie
        binding.movieImg.load(movieModel.image, 1)
        binding.movieName.text = movieModel.name
        binding.movieDescription.text = movieModel.description
        initRecycler()
        moviesViewModel.getSimilarMovies(movieModel.popularMovieId!!)
    }

    private fun initRecycler(){
        similarMoviesAdapter = SimilarMoviesAdapter()
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.similarMoviesRecycler.layoutManager = layoutManager
        binding.similarMoviesRecycler.adapter = similarMoviesAdapter
        setObservers()
    }

    private fun setObservers(){
        moviesViewModel.similarMovies.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    similarMoviesAdapter.addItems(it.data!!)
                }
                Resource.Status.NO_INTERNET -> {
                    toast(it.message!!)
                }
                Resource.Status.UNKNOWN_ERROR -> {
                    toast(it.message!!)
                }
                Resource.Status.ERROR -> {
                    toast(it.message!!)
                }
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}