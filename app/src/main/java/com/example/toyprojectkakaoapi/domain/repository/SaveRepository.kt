package com.example.toyprojectkakaoapi.domain.repository

import com.example.toyprojectkakaoapi.domain.entity.SearchDocumentEntity
import kotlinx.coroutines.flow.Flow

interface SaveRepository {
    suspend fun saveSearchEntity(item: SearchDocumentEntity)
    suspend fun loadSearchEntity() : Flow<List<SearchDocumentEntity>>
    suspend fun deleteSearchEntity(item: SearchDocumentEntity)
}