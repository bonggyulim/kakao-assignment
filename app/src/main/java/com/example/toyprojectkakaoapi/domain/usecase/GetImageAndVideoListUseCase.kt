package com.example.toyprojectkakaoapi.domain.usecase

import android.util.Log
import com.example.toyprojectkakaoapi.domain.entity.SearchMetaEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.entity.toSearchEntity
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageAndVideoListUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val saveRepository: SaveRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(query: String, page: Int): Flow<SearchEntity> =
        saveRepository.loadSearchEntity()
            // 이전의 Flow를 취소하고 새로운 Flow를 시작하여 최신 데이터를 반영
            .flatMapLatest { savedList ->
                val savedThumbnails = savedList.map { it.thumbnail }.toSet()
                val imageListFlow = searchRepository.getImageList(query, page).map { it.toSearchEntity() }
                val videoListFlow = searchRepository.getVideoList(query, page).map { it.toSearchEntity() }

                combine(imageListFlow, videoListFlow) { image, video ->
                    val combinedList = image.documents + video.documents
                    val updatedList = combinedList.map { entity ->
                        entity.copy(isLiked = savedThumbnails.contains(entity.thumbnail))
                    }
                    val combinedMeta = SearchMetaEntity(
                        image.meta.imageCount,
                        image.meta.imageEnd,
                        video.meta.imageCount,
                        video.meta.videoEnd
                    )
                    Log.d("sharedLog", "${updatedList.filter { it.isLiked }}")
                    SearchEntity(updatedList, combinedMeta)
                }
            }
}