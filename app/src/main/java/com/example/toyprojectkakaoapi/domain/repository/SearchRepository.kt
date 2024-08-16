package com.example.toyprojectkakaoapi.domain.repository

import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity

interface SearchRepository {
    suspend fun getVideoList(query: String): VideoEntity
    suspend fun getImageList(query: String): ImageEntity
}