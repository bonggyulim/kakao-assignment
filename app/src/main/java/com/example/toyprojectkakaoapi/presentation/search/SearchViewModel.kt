package com.example.toyprojectkakaoapi.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.usecase.DeleteSearchEntityUseCase
import com.example.toyprojectkakaoapi.domain.usecase.GetImageAndVideoListUseCase
import com.example.toyprojectkakaoapi.domain.usecase.LoadSearchEntityUseCase
import com.example.toyprojectkakaoapi.domain.usecase.SaveSearchEntityUseCase
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.model.SearchModel
import com.example.toyprojectkakaoapi.presentation.model.SearchModelList
import com.example.toyprojectkakaoapi.presentation.model.toEntity
import com.example.toyprojectkakaoapi.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val getImageAndVideoListUseCase: GetImageAndVideoListUseCase,
    private val saveSearchEntityUseCase: SaveSearchEntityUseCase,
    private val deleteSearchEntityUseCase: DeleteSearchEntityUseCase
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

    fun saveSearchEntity(searchModel: SearchModel) {
        viewModelScope.launch {
            saveSearchEntityUseCase.invoke(searchModel.toEntity())
        }
    }

    fun deleteSearchEntity(searchModel: SearchModel) {
        viewModelScope.launch {
            deleteSearchEntityUseCase.invoke(searchModel.toEntity())
        }
    }
}