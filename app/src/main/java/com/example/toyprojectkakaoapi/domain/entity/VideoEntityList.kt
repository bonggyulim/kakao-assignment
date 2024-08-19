package com.example.toyprojectkakaoapi.domain.entity

import java.util.Date
data class VideoEntityList(
    val documents: List<VideoEntity>?
)

data class VideoEntity(
    val thumbnail: String,
    val datetime: Date
)