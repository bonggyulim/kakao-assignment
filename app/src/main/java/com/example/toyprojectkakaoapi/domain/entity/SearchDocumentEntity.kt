package com.example.toyprojectkakaoapi.domain.entity

import java.util.Date

data class SearchEntity(
    val documents: List<SearchDocumentEntity>,
    val meta: SearchMetaEntity
)
data class SearchDocumentEntity(
    val thumbnail: String,
    val datetime: Date,
    val isLiked: Boolean = false
)

data class SearchMetaEntity(
    val imageCount : Int = 0,
    val imageEnd : Boolean = false,
    val videoCount : Int = 0,
    val videoEnd : Boolean = false
)