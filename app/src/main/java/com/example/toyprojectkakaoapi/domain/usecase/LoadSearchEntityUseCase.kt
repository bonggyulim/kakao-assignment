package com.example.toyprojectkakaoapi.domain.usecase

import com.example.toyprojectkakaoapi.domain.entity.SearchEntityList
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadSearchEntityUseCase @Inject constructor(
    private val saveRepository: SaveRepository
) {
    suspend operator fun invoke(): Flow<SearchEntityList> {
        return saveRepository.loadSearchEntity()
    }
}