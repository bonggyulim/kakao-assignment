package com.example.toyprojectkakaoapi.data.repository

import com.example.toyprojectkakaoapi.data.response.toEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    retrofit: Retrofit,
) : SearchRepository {
    private val imageSearch = retrofit.create(ImageSearchInterface::class.java)
    private val videoSearch = retrofit.create(VideoSearchInterface::class.java)
    override suspend fun getVideoList(query: String, page: Int): Flow<VideoEntity> = flow {
        val response = videoSearch.searchVideo(
            query = query,
            sort = "accuracy",
            page = page,
            size = 15
        ).toEntity()
        emit(response)
    }

    override suspend fun getImageList(query: String, page: Int): Flow<ImageEntity> = flow {
        val response = imageSearch.searchImage(
            query = query,
            sort = "accuracy",
            page = page,
            size = 15
        ).toEntity()
        emit(response)
    }
}