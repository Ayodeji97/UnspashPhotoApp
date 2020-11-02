package com.mylearning.unsplashphotoapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mylearning.unsplashphotoapp.databinding.UnsplashPhotoLoadStateFooterBinding

class UnspalshPhotoLoadStateAdapter (private val retry : () -> Unit ): LoadStateAdapter<UnspalshPhotoLoadStateAdapter.LoadStateViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = UnsplashPhotoLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {



    }


    inner class LoadStateViewHolder (private val binding: UnsplashPhotoLoadStateFooterBinding) : RecyclerView.ViewHolder (binding.root) {


        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind (loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
            }
        }

    }




}