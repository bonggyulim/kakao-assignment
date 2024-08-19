package com.example.toyprojectkakaoapi.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyprojectkakaoapi.domain.usecase.GetImageAndVideoListUseCase
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.model.SearchModelList
import com.example.toyprojectkakaoapi.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val getImageAndVideoListUseCase: GetImageAndVideoListUseCase,
) : ViewModel() {

    private val _getImageAndVideoState = MutableStateFlow<UiState<SearchModelList>>(UiState.Loading)
    val getImageAndVideoState: StateFlow<UiState<SearchModelList>> get() = _getImageAndVideoState

    fun getVideoAndImageList(query: String) {
        viewModelScope.launch {

            _getImageAndVideoState.value = UiState.Loading

            getImageAndVideoListUseCase.invoke(query)
                .catch { e ->
                    _getImageAndVideoState.value = UiState.Error(e.toString())
                }
                .collect { entity ->
                    _getImageAndVideoState.value = UiState.Success(entity.toModel())
                }
        }
    }

}