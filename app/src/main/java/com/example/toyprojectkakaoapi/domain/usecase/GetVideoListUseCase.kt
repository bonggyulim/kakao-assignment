package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.VideoEntity
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<VideoEntity> {
        return searchRepository.getVideoList(query)
    }
}
