package com.bitopi.switchcontrol.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitopi.switchcontrol.databinding.AdapterMovieBinding
import com.bitopi.switchcontrol.model.Movie
import com.bitopi.switchcontrol.model.utils.ValidationUtil
import com.bumptech.glide.Glide
import javax.xml.validation.ValidatorHandler

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var movieList = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>){
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        if (ValidationUtil.validateMovie(movie)) {
            holder.binding.name.text = movie.name
            Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
        }
    }


}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}
