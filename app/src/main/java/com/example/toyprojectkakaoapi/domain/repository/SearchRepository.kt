package com.example.toyprojectkakaoapi.domain.repository

import com.example.toyprojectkakaoapi.domain.entity.ImageEntityList
import com.example.toyprojectkakaoapi.domain.entity.VideoEntityList
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getVideoList(query: String): Flow<VideoEntityList>
    suspend fun getImageList(query: String): Flow<ImageEntityList>
}