package com.example.toyprojectkakaoapi.domain.repository

import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getVideoList(query: String): Flow<VideoEntity>
    suspend fun getImageList(query: String): Flow<ImageEntity>
}