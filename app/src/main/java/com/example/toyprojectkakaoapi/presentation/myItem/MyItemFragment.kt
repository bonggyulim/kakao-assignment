package com.example.toyprojectkakaoapi.presentation.myItem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toyprojectkakaoapi.databinding.FragmentMyItemBinding
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.search.SearchRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyItemFragment : Fragment() {
    private var _binding: FragmentMyItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyItemViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoadItemsState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.loadItems()
        }
    }

    private fun observeLoadItemsState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadItemsState.collectLatest { state ->
                    when (state) {
                        is UiState.Loading -> {
                            Log.d("apiLog", "loading")
                        }

                        is UiState.Success -> {
                            Log.d("apiLog", "success")
                            val data = state.data.documents?.toMutableList()
                            val myItemAdapter = MyItemRVAdapter(data ?: mutableListOf())
                            binding.rvSearch.adapter = myItemAdapter
                            binding.rvSearch.layoutManager = GridLayoutManager(
                                requireContext(),
                                3
                            )

                            myItemAdapter.itemClick = object : MyItemRVAdapter.ItemClick {
                                override fun itemClick(position: Int) {
                                    viewModel.deleteItem(data!![position])
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
    }

}