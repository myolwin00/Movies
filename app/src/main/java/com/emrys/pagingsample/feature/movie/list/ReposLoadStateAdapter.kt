package com.emrys.pagingsample.feature.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emrys.pagingsample.R
import com.emrys.pagingsample.databinding.ItemLoadStateBinding

class ReposLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ReposLoadStateAdapter.ReposLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ReposLoadStateViewHolder {
        return ReposLoadStateViewHolder.create(parent, retry)
    }

    class ReposLoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            // todo: show error info
//            if (loadState is LoadState.Error) {
//                binding.errorMsg.text = loadState.error.localizedMessage
//            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
//            binding.errorMsg.visibility = toVisibility(loadState !is LoadState.Loading)
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_load_state, parent, false)
                val binding = ItemLoadStateBinding.bind(view)
                return ReposLoadStateViewHolder(binding, retry)
            }
        }
    }
}