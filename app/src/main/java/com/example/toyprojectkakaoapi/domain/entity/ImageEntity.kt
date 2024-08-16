package com.example.toyprojectkakaoapi.domain.entity

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.Date

data class ImageEntity(
    val documents: List<ImageDocumentsEntity>?
)

data class ImageDocumentsEntity(
    @SerializedName("thumbnail_url")
    val thumbnail: URL,
    val datetime: Date
)