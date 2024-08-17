package com.example.toyprojectkakaoapi.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyprojectkakaoapi.domain.usecase.GetImageListUseCase
import com.example.toyprojectkakaoapi.domain.usecase.GetVideoListUseCase
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.model.ImageModel
import com.example.toyprojectkakaoapi.presentation.model.VideoModel
import com.example.toyprojectkakaoapi.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
    private val getVideoListUseCase: GetVideoListUseCase
) : ViewModel() {

    private val _getVideoState = MutableStateFlow<UiState<VideoModel>>(UiState.Loading)
    val getVideoState: StateFlow<UiState<VideoModel>> get() = _getVideoState

    private val _getImageState = MutableStateFlow<UiState<ImageModel>>(UiState.Loading)
    val getImageState: StateFlow<UiState<ImageModel>> get() = _getImageState

    fun getVideoList(query: String) {
        viewModelScope.launch {

            _getVideoState.value = UiState.Loading

            getVideoListUseCase.invoke(query)
                .catch { e ->
                    _getVideoState.value = UiState.Error(e.toString())
                }
                .collect { entity ->
                    _getVideoState.value = UiState.Success(entity.toModel())
                }
        }
    }

    fun getImageList(query: String) {
        viewModelScope.launch {

            _getImageState.value = UiState.Loading

            getImageListUseCase.invoke(query)
                .catch { e ->
                    _getImageState.value = UiState.Error(e.toString())
                }
                .collect { entity ->
                    _getImageState.value = UiState.Success(entity.toModel())
                }
        }
    }


}