package com.example.toyprojectkakaoapi.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toyprojectkakaoapi.databinding.FragmentSearchBinding
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.model.SearchModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private var lastQuery: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        observeVideoStateAndImageState()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            if (lastQuery != null) {
                viewModel.getVideoAndImageList(lastQuery!!)
            }
        }
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
                        val data = state.data.documents?.sortedByDescending { it.datetime }
                        val searchAdapter = SearchRVAdapter(data ?: listOf())
                        binding.rvSearch.adapter = searchAdapter
                        binding.rvSearch.layoutManager = GridLayoutManager(
                            requireContext(),
                            3
                        )

                        searchAdapter.itemClick = object : SearchRVAdapter.ItemClick {
                            override fun itemClick(position: Int, isLiked: Boolean) {
                                if (isLiked) {
                                    viewModel.saveSearchEntity(data!![position])
                                } else {
                                    viewModel.deleteSearchEntity(data!![position])
                                }
                            }
                        }
                    }

                    is UiState.Error -> {
                        Log.d("apiLog", state.message)
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
                lastQuery = query
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