package com.excample.hilt.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.excample.hilt.data.models.PostModel
import com.excample.hilt.databinding.ItemPostsBinding

class GetAdapter : ListAdapter<PostModel, GetAdapter.GetViewHolder>(diffUtil) {

    inner class GetViewHolder(private val binding: ItemPostsBinding) : ViewHolder(binding.root) {

        fun onBind(item: PostModel) {
            Glide.with(binding.ivImage.context)
                .load(item.url + ".png")
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetViewHolder {
        return GetViewHolder(
            ItemPostsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GetViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PostModel>() {
            override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}