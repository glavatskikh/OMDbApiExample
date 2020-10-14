package com.glavatskikh.omdbapiexample.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.glavatskikh.omdbapiexample.R
import com.glavatskikh.omdbapiexample.api.response.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    private val clickListener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies: MutableList<Movie> = mutableListOf()

    fun setMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, clickListener: (movie: Movie) -> Unit) {
            with(itemView) {
                item_movie_title.text = movie.title
                item_movie_image.load(movie.poster)
                setOnClickListener {
                    clickListener(movie)
                }
            }
        }
    }
}
