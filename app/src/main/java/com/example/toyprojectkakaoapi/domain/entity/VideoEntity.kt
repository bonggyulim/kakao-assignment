package com.example.toyprojectkakaoapi.domain.entity

import java.net.URL
import java.util.Date
data class VideoEntity(
    val documents: List<VideoDocumentEntity>?
)

data class VideoDocumentEntity(
    val thumbnail: URL,
    val datetime: Date
)