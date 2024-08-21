package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchMetaEntity
import com.example.toyprojectkakaoapi.domain.entity.toSearchEntity
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetImageAndVideoListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String, page: Int): Flow<SearchEntity>  {
        val imageListFlow = searchRepository.getImageList(query, page).map { it.toSearchEntity() }
        val videoListFlow = searchRepository.getVideoList(query, page).map { it.toSearchEntity() }

        return combine(imageListFlow, videoListFlow) { image, video ->
            val combinedList = image.documents + video.documents
            val combinedMeta = SearchMetaEntity(
                image.meta.imageCount,
                image.meta.imageEnd,
                video.meta.imageCount,
                video.meta.videoEnd
            )
            SearchEntity(combinedList, combinedMeta)
        }
    }
}