package com.example.toyprojectkakaoapi.domain.entity

import java.util.Date

data class ImageEntityList(
    val documents: List<ImageEntity>?
)

data class ImageEntity(
    val thumbnail: String,
    val datetime: Date
)