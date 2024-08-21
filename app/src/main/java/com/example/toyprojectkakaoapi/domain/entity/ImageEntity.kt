package com.example.toyprojectkakaoapi.domain.entity

import java.util.Date

data class ImageEntity(
    val documents: List<ImageDocumentEntity>,
    val meta: ImageMetaEntity
)

data class ImageDocumentEntity(
    val thumbnail: String,
    val datetime: Date
)

data class ImageMetaEntity(
    val count : Int,
    val isEnd : Boolean
)