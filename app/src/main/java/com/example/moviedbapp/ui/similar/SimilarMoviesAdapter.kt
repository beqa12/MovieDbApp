package com.example.moviedbapp.ui.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.SimilarMovieOneItemBinding
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.load

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMovieHolder>() {
    private var similarMovies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieHolder {
        val binding = SimilarMovieOneItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarMovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return similarMovies.size
    }

    override fun onBindViewHolder(holder: SimilarMovieHolder, position: Int) {
        val model = similarMovies[position]
        holder.setData(model)
    }

    inner class SimilarMovieHolder(private val oneItemBinding: SimilarMovieOneItemBinding): RecyclerView.ViewHolder(oneItemBinding.root){
        fun setData(movieModel: Movie){
            oneItemBinding.similarMovieImg.load(movieModel.image, corner = 1)
            oneItemBinding.similarMovieName.text = movieModel.name
        }
    }

    fun addItems(similarMovies: List<Movie>){
        this.similarMovies.clear()
        this.similarMovies.addAll(similarMovies)
        notifyDataSetChanged()
    }
}