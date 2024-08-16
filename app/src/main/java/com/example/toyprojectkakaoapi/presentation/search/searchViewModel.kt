package com.example.toyprojectkakaoapi.presentation.search

import androidx.lifecycle.ViewModel
import com.example.toyprojectkakaoapi.domain.usecase.GetImageListUseCase
import com.example.toyprojectkakaoapi.domain.usecase.GetVideoListUseCase
import com.example.toyprojectkakaoapi.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class searchViewModel  @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
    private val getVideoListUseCase: GetVideoListUseCase
) : ViewModel() {

    private val _getVideoState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val getVideoState: StateFlow<UiState<Boolean>> get() = _getVideoState

}