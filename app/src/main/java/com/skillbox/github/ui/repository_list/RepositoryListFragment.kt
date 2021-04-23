package com.skillbox.github.ui.repository_list

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skillbox.github.R
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryListFragment : Fragment(R.layout.fragment_repository_list) {
    private val repositoryListViewModel: RepositoryListViewModel by viewModels()
    private var repositoryAdapter: RepositoryAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        repositoryAdapter = RepositoryAdapter { position ->
            findNavController().navigate(
                RepositoryListFragmentDirections.actionRepositoryListFragmentToRepositoryDetailFragment(
                    repositoryAdapter!!.currentList[position]
                )
            )
        }

        with(recyclerView) {
            this.adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

        repositoryListViewModel.exception.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }

        repositoryListViewModel.isLoading.observe(viewLifecycleOwner) {
            setVisibility(it)
        }

        repositoryListViewModel.listRepository.observe(viewLifecycleOwner) {
            repositoryAdapter?.submitList(it)
        }

        repositoryListViewModel.getRepositoryList()
    }

    private fun setVisibility(visibility: Boolean) {
        recyclerView.isVisible = visibility.not()
        progressBar.isVisible = visibility
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryAdapter = null
    }
}
