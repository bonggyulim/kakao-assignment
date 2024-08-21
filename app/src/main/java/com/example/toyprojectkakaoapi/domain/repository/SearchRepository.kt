package com.example.toyprojectkakaoapi.domain.repository

import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getVideoList(query: String, page: Int): Flow<VideoEntity>
    suspend fun getImageList(query: String, page: Int): Flow<ImageEntity>
}