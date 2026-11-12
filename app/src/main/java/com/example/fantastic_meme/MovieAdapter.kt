package com.example.fantastic_meme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fantastic_meme.databinding.MovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter(var movies: List<Response>, val listener: (Int) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.render(movie)
        holder.itemView.setOnClickListener {
            listener(position)
        }
    }
    override fun getItemCount(): Int {
        return movies.size
    }
    fun update(movies: List<Response>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}
class MovieViewHolder(val binding: MovieBinding): RecyclerView.ViewHolder(binding.root) {
    fun render(movie: Response) {
        Picasso.get().load(movie.Poster).into(binding.poster)
        binding.name.text = movie.Title
        binding.year.text = movie.Year.toString()
    }
}