package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.SearchDocumentEntity
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadSearchEntityUseCase @Inject constructor(
    private val saveRepository: SaveRepository
) {
    suspend operator fun invoke(): Flow<List<SearchDocumentEntity>> {
        return saveRepository.loadSearchEntity()
    }
}