package com.example.toyprojectkakaoapi.data.repository

import com.example.toyprojectkakaoapi.data.response.toEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntityList
import com.example.toyprojectkakaoapi.domain.entity.VideoEntityList
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
    override suspend fun getVideoList(query: String): Flow<VideoEntityList> = flow {
        val respose = videoSearch.searchVideo(
            query = query,
            sort = "accuracy"
        ).toEntity()
        emit(respose)
    }

    override suspend fun getImageList(query: String): Flow<ImageEntityList> = flow {
        val respose = imageSearch.searchImage(
            query = query,
            sort = "accuracy"
        ).toEntity()
        emit(respose)
    }
}