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
import androidx.recyclerview.widget.RecyclerView
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
    private var page = 1
    private var lastQuery = ""
    private var imageEnd = false
    private var videoEnd = false
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

                        imageEnd = state.data.meta.imageEnd
                        videoEnd = state.data.meta.videoEnd

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
                viewLifecycleOwner.lifecycleScope.launch {
                    if (query != null) {
                        lastQuery = query
                        viewModel.getVideoAndImageList(query, page)
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

    private fun getNextImageAndVideo() {
        page++
        if ()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getVideoAndImageList(lastQuery, page)
        }
    }

    private fun initScrollListener() {
        binding.rvSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.rvSearch.layoutManager

            }
        })
    }


}