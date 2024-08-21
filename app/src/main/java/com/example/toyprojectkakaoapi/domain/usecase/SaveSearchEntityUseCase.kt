package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.SearchDocumentEntity
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import javax.inject.Inject

class SaveSearchEntityUseCase @Inject constructor(
    private val saveRepository: SaveRepository
) {
    suspend operator fun invoke(searchDocumentEntity: SearchDocumentEntity) {
        saveRepository.saveSearchEntity(searchDocumentEntity)
    }
}