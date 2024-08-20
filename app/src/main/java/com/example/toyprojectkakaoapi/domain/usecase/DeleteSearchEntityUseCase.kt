package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import javax.inject.Inject

class DeleteSearchEntityUseCase @Inject constructor(
    private val saveRepository: SaveRepository
) {
    suspend operator fun invoke(searchEntity: SearchEntity) {
        saveRepository.deleteSearchEntity(searchEntity)
    }
}
