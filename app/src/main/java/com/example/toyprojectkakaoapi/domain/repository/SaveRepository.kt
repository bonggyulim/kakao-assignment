package com.example.toyprojectkakaoapi.domain.repository

import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchEntityList
import kotlinx.coroutines.flow.Flow

interface SaveRepository {
    suspend fun saveSearchEntity(item: SearchEntity)
    suspend fun loadSearchEntity() : Flow<SearchEntityList>
    suspend fun deleteSearchEntity(item: SearchEntity)
}