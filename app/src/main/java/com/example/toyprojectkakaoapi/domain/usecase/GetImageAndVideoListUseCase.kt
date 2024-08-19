package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.SearchEntityList
import com.example.toyprojectkakaoapi.domain.entity.toSearchEntity
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetImageAndVideoListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(query: String): Flow<SearchEntityList> {
        val imageFlow = searchRepository.getImageList(query).map { it.toSearchEntity() }
        val videoFlow = searchRepository.getVideoList(query).map { it.toSearchEntity() }

        return flowOf(imageFlow, videoFlow).flatMapMerge { it }.toList().asFlow()
    }
}
