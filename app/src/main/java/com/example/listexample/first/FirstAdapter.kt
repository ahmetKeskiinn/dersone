package com.example.listexample.first

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listexample.MovieItem
import com.example.listexample.R
import com.example.listexample.databinding.ItemSecondBinding


class FirstAdapter(
    val context: Context,
    val itemClicked: ItemClicked
) :
    ListAdapter<MovieItem, RecyclerView.ViewHolder>(
        object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            ItemSecondBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder) {
            is FirstAdapter.ImageViewHolder -> holder.bind(currentItem)
        }
    }

    inner class ImageViewHolder(val itemBinding: ItemSecondBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(currentItem: MovieItem) {
            itemBinding.nameTw.text = currentItem.name
            itemBinding.contentTw.text = currentItem.content
            changeBg(currentItem)
            itemBinding.root.setOnClickListener {
                itemClicked.isItemClicked(currentItem)
            }
        }

        fun changeBg(item: MovieItem) {
            if (item.isSelected == true) {
                itemBinding.container.setBackgroundResource(R.drawable.bg_selected)
            } else {
                itemBinding.container.setBackgroundResource(R.drawable.bg_notselected)
            }
        }
    }
}
