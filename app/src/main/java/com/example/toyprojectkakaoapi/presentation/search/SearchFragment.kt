package com.example.toyprojectkakaoapi.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.toyprojectkakaoapi.R
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
        observeGetImageState()
        observeGetVideoState()
        initSearchView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeGetImageState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getImageState.collectLatest { state ->
                when (state) {
                    is UiState.Loading -> {
                        Log.d("apiLog", "loading")
                    }
                    is UiState.Success -> {
                        Log.d("apiLog", "success")
                    }
                    is UiState.Error -> {
                        Log.d("apiLog", "error")
                    }
                }
            }
        }
    }

    private fun observeGetVideoState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getVideoState.collectLatest { state ->
                when (state) {
                    is UiState.Loading -> {
                        Log.d("apiLog", "loading")
                    }
                    is UiState.Success -> {
                        Log.d("apiLog", "success")
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
                // @TODO
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // @TODO
                return true
            }
        })
    }

}