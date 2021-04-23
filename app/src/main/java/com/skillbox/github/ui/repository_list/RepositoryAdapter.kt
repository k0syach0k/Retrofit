package com.skillbox.github.ui.repository_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.skillbox.github.R
import com.skillbox.github.models.Repository
import com.skillbox.github.utils.inflate

class RepositoryAdapter(private val onItemClicked: (position: Int) -> Unit) : ListAdapter<Repository, RepositoryHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        return RepositoryHolder(onItemClicked, parent.inflate(R.layout.item_repository))
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LocationDiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}
