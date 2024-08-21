package com.example.toyprojectkakaoapi.presentation.myItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyprojectkakaoapi.domain.usecase.DeleteSearchEntityUseCase
import com.example.toyprojectkakaoapi.domain.usecase.LoadSearchEntityUseCase
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.model.SearchDocumentModel
import com.example.toyprojectkakaoapi.presentation.model.toEntity
import com.example.toyprojectkakaoapi.presentation.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyItemViewModel  @Inject constructor(
    private val loadSearchEntityUseCase: LoadSearchEntityUseCase,
    private val deleteSearchEntityUseCase: DeleteSearchEntityUseCase
) : ViewModel() {

    private val _loadItemsState = MutableStateFlow<UiState<List<SearchDocumentModel>>>(UiState.Loading)
    val loadItemsState: StateFlow<UiState<List<SearchDocumentModel>>> get() = _loadItemsState

    fun loadItems() {
        viewModelScope.launch {

            _loadItemsState.value = UiState.Loading

            loadSearchEntityUseCase.invoke()
                .catch { e ->
                    _loadItemsState.value = UiState.Error(e.toString())
                }
                .collect { entity ->
                    _loadItemsState.value = UiState.Success(entity.map { it.toModel() })
                }
        }
    }

    fun deleteItem(searchDocumentModel: SearchDocumentModel) {
        viewModelScope.launch {
            deleteSearchEntityUseCase.invoke(searchDocumentModel.toEntity())
        }
    }

}