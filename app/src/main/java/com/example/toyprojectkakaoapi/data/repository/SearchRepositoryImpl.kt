package com.example.toyprojectkakaoapi.data.repository

import com.example.toyprojectkakaoapi.data.response.toEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import retrofit2.Retrofit
import retrofit2.http.Query
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    retrofit: Retrofit,
) : SearchRepository {
    private val imageSearch = retrofit.create(ImageSearchInterface::class.java)
    private val videoSearch = retrofit.create(VideoSearchInterface::class.java)
    override suspend fun getVideoList(query: String): VideoEntity {
        return videoSearch.searchVideo(
            query = query,
            sort = "accuracy"
        ).toEntity()
    }

    override suspend fun getImageList(query: String): ImageEntity {
        return imageSearch.searchImage(
            query = query,
            sort = "accuracy"
        ).toEntity()
    }
}