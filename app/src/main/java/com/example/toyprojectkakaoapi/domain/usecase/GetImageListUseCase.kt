package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<ImageEntity> {
        return searchRepository.getImageList(query)
    }
}
