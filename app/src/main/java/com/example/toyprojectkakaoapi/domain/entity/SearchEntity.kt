package com.example.toyprojectkakaoapi.domain.entity

import java.util.Date

data class SearchEntityList(
    val documents: List<SearchEntity>?
)
data class SearchEntity(
    val thumbnail: String,
    val datetime: Date
)