package com.skillbox.github.ui.repository_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.github.models.Repository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.*

class RepositoryHolder(
    onItemClicked: (position: Int) -> Unit,
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.setOnClickListener { onItemClicked(adapterPosition) }
    }

    fun bind(repository: Repository) {
        name.text = repository.name
        description.text = repository.description
    }
}
