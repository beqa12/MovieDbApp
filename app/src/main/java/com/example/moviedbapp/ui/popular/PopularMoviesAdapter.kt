package com.example.moviedbapp.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.PopularMoviesOneItemBinding
import com.example.moviedbapp.domain.model.Movie
import com.example.moviedbapp.load
import java.util.*
import kotlin.collections.ArrayList

class PopularMoviesAdapter (private val clickListener: (Movie, View)-> Unit): RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesHolder>(),
    Filterable {
    private var popularMovies = ArrayList<Movie>()
    private var popularMoviesFilteredList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesHolder {
        val itemBinding = PopularMoviesOneItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    override fun onBindViewHolder(holder: PopularMoviesHolder, position: Int) {
        val popularMovie = popularMovies[position]
        holder.setData(popularMovie)
    }

    fun addMovies(movies: List<Movie>){
        this.popularMovies.addAll(movies)
        this.popularMoviesFilteredList.addAll(movies)
        notifyDataSetChanged()
    }
    inner class PopularMoviesHolder(private var itemBinding: PopularMoviesOneItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun setData(movieModel: Movie){
            itemBinding.popularMovieName.text = movieModel.name
            itemBinding.movieDate.text = movieModel.date
            itemBinding.ratingBar.rating = movieModel.rating!!.toFloat()
            itemBinding.popularMovieImg.load(movieModel.image!!)
            itemBinding.root.setOnClickListener{
                clickListener(movieModel, itemBinding.popularMovieImg)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint.isEmpty()){
                    filterResults.count = popularMoviesFilteredList.size
                    filterResults.values = popularMoviesFilteredList
                }else{
                    val inputText = constraint.toString().toLowerCase()
                    val list = ArrayList<Movie>()
                    for (item in popularMoviesFilteredList){
                        if (item.name!!.lowercase(Locale.getDefault()).contains(inputText)){
                            list.add(item)
                        }
                    }
                    filterResults.count = list.size
                    filterResults.values = list
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                popularMovies = results?.values as ArrayList<Movie>
                notifyDataSetChanged()
            }

        }
    }
}