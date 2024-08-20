package com.example.toyprojectkakaoapi.domain.usecase

import android.util.Log
import com.example.toyprojectkakaoapi.domain.entity.SearchEntityList
import com.example.toyprojectkakaoapi.domain.entity.toSearchEntity
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageAndVideoListUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val saveRepository: SaveRepository
) {
    suspend operator fun invoke(query: String): Flow<SearchEntityList> = flow {
        val savedThumbnails = saveRepository.loadSearchEntity()
            .map { it.documents?.map { doc -> doc.thumbnail }?.toSet() ?: emptySet() }
            .first()

        val imageListFlow = searchRepository.getImageList(query).map { it.toSearchEntity() }
        val videoListFlow = searchRepository.getVideoList(query).map { it.toSearchEntity() }

        combine(imageListFlow, videoListFlow) { imageList, videoList ->
            val combinedList = imageList.documents.orEmpty() + videoList.documents.orEmpty()

            val updatedList = combinedList.map { entity ->
                entity.copy(isLiked = savedThumbnails.contains(entity.thumbnail))
            }
            SearchEntityList(updatedList)

        }.collect { searchEntityList ->
            emit(searchEntityList)
        }
    }
}