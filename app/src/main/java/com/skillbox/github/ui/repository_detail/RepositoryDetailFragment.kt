package com.skillbox.github.ui.repository_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.skillbox.github.R
import kotlinx.android.synthetic.main.fragment_repository_detail.*

class RepositoryDetailFragment : Fragment(R.layout.fragment_repository_detail) {
    private val args: RepositoryDetailFragmentArgs by navArgs()

    private val repositoryDetailViewModel: RepositoryDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = args.repository

        Glide.with(requireView())
            .load(repository.owner.avatar)
            .placeholder(R.drawable.ic_no_image)
            .into(avatarImageView)

        ownerNameTextView.text = repository.owner.login
        repositoryNameTextView.text = repository.name
        repositoryDescriptionTextView.text = repository.description

        repositoryDetailViewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
        }

        repositoryDetailViewModel.exception.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        repositoryDetailViewModel.repositoryStar.observe(viewLifecycleOwner) {
            when (it) {
                true -> starButton.setImageResource(R.drawable.btn_star_big_on)
                else -> starButton.setImageResource(R.drawable.btn_star_big_off)
            }
        }

        repositoryDetailViewModel.getStarState(repository.owner.login, repository.name)

        starButton.setOnClickListener {
            repositoryDetailViewModel.changeStarState(repository.owner.login, repository.name)
        }
    }
}
