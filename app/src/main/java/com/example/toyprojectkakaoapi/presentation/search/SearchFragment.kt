package com.example.toyprojectkakaoapi.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toyprojectkakaoapi.databinding.FragmentSearchBinding
import com.example.toyprojectkakaoapi.presentation.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVideoStateAndImageState()
        initSearchView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeVideoStateAndImageState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getImageAndVideoState.collectLatest { state ->
                when (state) {
                    is UiState.Loading -> {
                        Log.d("apiLog", "loading")
                    }
                    is UiState.Success -> {
                        Log.d("apiLog", "success")
                        val searchAdapter = state.data.documents?.let { doc -> SearchRVAdapter(doc.sortedByDescending { it.datetime }) }
                        binding.rvSearch.adapter = searchAdapter
                        binding.rvSearch.layoutManager = GridLayoutManager(
                            requireContext(),
                            2
                        )
                    }
                    is UiState.Error -> {
                        Log.d("apiLog", "error")
                    }
                }
            }
        }
    }

    private fun initSearchView() {
        binding.searchView.isSubmitButtonEnabled = true
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("query", "${binding.searchView.query}")
                viewLifecycleOwner.lifecycleScope.launch {
                    if (query != null) {
                        viewModel.getVideoAndImageList(query)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO
                return true
            }
        })

    }

}