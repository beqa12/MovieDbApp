package com.example.moviedbapp.ui.popular

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.R
import com.example.moviedbapp.animations.AnimationsUtils
import com.example.moviedbapp.databinding.ActivityMainBinding
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.focusChangeAnimation
import com.example.moviedbapp.network.model.Resource
import com.example.moviedbapp.toast
import com.example.moviedbapp.ui.similar.SimilarMoviesActivity
import com.example.moviedbapp.utils.KeyboardHelper
import com.example.moviedbapp.utils.POPULAR_MOVIE_MODEL_KEY
import com.example.moviedbapp.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoviesActivity : AppCompatActivity() {
    val moviesViewModel: MoviesViewModel by viewModel()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private var loading = false
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        initUI()
    }

    private fun initUI() {
        popularMoviesAdapter = PopularMoviesAdapter { popularMovieModel, imageView ->
            popularMovieModelClicked(
                popularMovieModel,
                imageView
            )
        }
        mLayoutManager = LinearLayoutManager(this)
        binding.popularMoviesRecycler.layoutManager = mLayoutManager
        binding.popularMoviesRecycler.adapter = popularMoviesAdapter
        moviesViewModel.getPopularMovies(page)
        setListeners()
    }

    private fun setListeners() {
        binding.filterEditText.focusChangeAnimation(binding.filterBackBtn)
        binding.filterBackBtn.setOnClickListener {
            AnimationsUtils.focusChangeAnimation(
                binding.filterEditText,
                binding.filterBackBtn,
                400,
                true
            )
            KeyboardHelper.hideKeyboard(this, binding.filterEditText)
        }
        binding.filterEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                popularMoviesAdapter.filter.filter(s)
            }
        })
        binding.popularMoviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleCount = mLayoutManager.childCount
                    val totalCount = mLayoutManager.itemCount
                    val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()

                    if (!loading && (visibleCount + pastVisibleItems) >= totalCount) {
                        loading = true
                        page++
                        moviesViewModel.getPopularMovies(page)
                    }
                }
            }
        })
    }

    private fun setObservers() {
        moviesViewModel.popularMovies.observe(this, Observer {
            loading = false
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    popularMoviesAdapter.addMovies(it.data!!)
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

    private fun popularMovieModelClicked(movieModel: Movie, imageView: View) {
        startSimilarMoviesActivity(movieModel, imageView)
    }

    private fun startSimilarMoviesActivity(movieModel: Movie, imageView: View) {
        val intent = Intent(this, SimilarMoviesActivity::class.java)
        intent.putExtra(POPULAR_MOVIE_MODEL_KEY, movieModel)
        val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, imageView, resources.getString(
                R.string.img_transition_name
            )
        ).toBundle()
        startActivity(intent, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}