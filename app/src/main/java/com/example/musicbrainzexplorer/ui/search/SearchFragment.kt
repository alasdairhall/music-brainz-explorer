package com.example.musicbrainzexplorer.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicbrainzexplorer.databinding.FragmentSearchBinding
import com.example.musicbrainzexplorer.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.searchResultsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.searchButton.setOnClickListener {
            search()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search()
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?) = true
        })

        viewModel.artists.observe(viewLifecycleOwner) { artists ->
            binding.searchResultsRecyclerView.adapter =
                SearchResultAdapter(artists, ::navigateToDetail)
        }
    }

    private fun search() {
        viewModel.onClickSearch(binding.searchView.query.toString())
    }

    private fun navigateToDetail(id: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }
}