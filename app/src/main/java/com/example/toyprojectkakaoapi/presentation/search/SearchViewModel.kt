package com.example.toyprojectkakaoapi.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toyprojectkakaoapi.domain.usecase.DeleteSearchEntityUseCase
import com.example.toyprojectkakaoapi.domain.usecase.GetImageAndVideoListUseCase
import com.example.toyprojectkakaoapi.domain.usecase.LoadSearchEntityUseCase
import com.example.toyprojectkakaoapi.domain.usecase.SaveSearchEntityUseCase
import com.example.toyprojectkakaoapi.presentation.UiState
import com.example.toyprojectkakaoapi.presentation.model.SearchDocumentModel
import com.example.toyprojectkakaoapi.presentation.model.SearchModel
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
    private val deleteSearchEntityUseCase: DeleteSearchEntityUseCase,
    private val loadSearchEntityUseCase: LoadSearchEntityUseCase
) : ViewModel() {

    private val _getImageAndVideoState = MutableStateFlow<UiState<SearchModel>>(UiState.Loading)
    val getImageAndVideoState: StateFlow<UiState<SearchModel>> get() = _getImageAndVideoState

    private var currentSearchModel: SearchModel? = null
    private var savedThumbnails: Set<String> = emptySet()

    init {
        // ViewModel 초기화 시 저장된 좋아요 상태를 감지
        observeSavedThumbnails()
    }

    private fun observeSavedThumbnails() {
        viewModelScope.launch {
            loadSearchEntityUseCase.invoke().collectLatest { savedList ->
                savedThumbnails = savedList.map { it.thumbnail }.toSet()

                currentSearchModel?.let { currentModel ->
                    val updatedDocuments = currentModel.documents.map { document ->
                        document.copy(isLiked = savedThumbnails.contains(document.thumbnail))
                    }
                    currentSearchModel = currentModel.copy(documents = updatedDocuments)
                    _getImageAndVideoState.value = UiState.Success(currentSearchModel!!)
                }
            }
        }
    }

    fun getVideoAndImageList(query: String, page: Int) {
        viewModelScope.launch {
            _getImageAndVideoState.value = UiState.Loading

            getImageAndVideoListUseCase.invoke(query, page)
                .catch { e ->
                    _getImageAndVideoState.value = UiState.Error(e.toString())
                }
                .collect { entity ->
                    val newSearchModel = entity.toModel()
                    val sortedDocuments = newSearchModel.documents.sortedByDescending { it.datetime }
                    val updatedList = sortedDocuments.map { entity ->
                        entity.copy(isLiked = savedThumbnails.contains(entity.thumbnail))
                    }
                    val sortedSearchModel = newSearchModel.copy(documents = updatedList)


                    currentSearchModel =
                        if (currentSearchModel == null) {
                            sortedSearchModel
                        }
                        else {
                            currentSearchModel!!
                                .copy(
                                    documents = currentSearchModel!!.documents + sortedSearchModel.documents,
                                    meta = sortedSearchModel.meta
                                )
                        }

                    _getImageAndVideoState.value = UiState.Success(currentSearchModel!!)
                }
        }
    }

    fun saveSearchEntity(searchDocumentModel: SearchDocumentModel) {
        viewModelScope.launch {
            saveSearchEntityUseCase.invoke(searchDocumentModel.toEntity())
        }
    }

    fun deleteSearchEntity(searchDocumentModel: SearchDocumentModel) {
        viewModelScope.launch {
            deleteSearchEntityUseCase.invoke(searchDocumentModel.toEntity())
        }
    }

    fun removeCurrentSearchModel() {
        currentSearchModel = null
    }
}