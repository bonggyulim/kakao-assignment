package com.example.toyprojectkakaoapi.domain.entity

import java.net.URL
import java.util.Date
data class VideoEntity(
    val documents: List<VideoDocumentsEntity>?
)

data class VideoDocumentsEntity(
    val thumbnail: URL,
    val datetime: Date
)