package com.example.toyprojectkakaoapi.data.repository

import com.example.toyprojectkakaoapi.data.response.ImageResponse
import com.example.toyprojectkakaoapi.data.response.VideoResponse
import com.example.toyprojectkakaoapi.util.AUTH_HEADER
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageSearchInterface {
    @GET("v2/search/image")
    suspend fun searchImage(
        @Header("Authorization") apiKey: String = "KakaoAK $AUTH_HEADER",
        @Query("query") query: String,
        @Query("sort") sort: String
    ): ImageResponse
}

interface VideoSearchInterface {
    @GET("v2/search/vclip")
    suspend fun searchVideo(
        @Header("Authorization") apiKey: String = "KakaoAK $AUTH_HEADER",
        @Query("query") query: String,
        @Query("sort") sort : String
    ): VideoResponse
}