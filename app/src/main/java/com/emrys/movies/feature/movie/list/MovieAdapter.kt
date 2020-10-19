package com.emrys.movies.feature.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emrys.movies.R
import com.emrys.movies.databinding.ItemMovieBinding
import com.emrys.movies.domain.model.Movie

class MovieAdapter(
    private val onAction: (MovieAction) -> Unit
): PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {  holder.bind(it) }
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private var _data: Movie? = null
        private val data: Movie
            get() = _data!!

        init {
            itemView.setOnClickListener {
                onAction(MovieAction.Favorite(data.id))
            }
        }

        fun bind(movie: Movie) {
            _data = movie
            Glide.with(binding.root)
                .load(movie.image)
                .placeholder(R.drawable.movie_placeholder)
                .into(binding.ivPoster)
            binding.ivFavStatus.setImageResource(if (movie.isFavorite) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_baseline_favorite_border_24
            })
        }
    }
}

object MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}