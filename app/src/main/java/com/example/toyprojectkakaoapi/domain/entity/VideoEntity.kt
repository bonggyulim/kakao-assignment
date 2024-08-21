package com.example.toyprojectkakaoapi.domain.entity

import java.util.Date
data class VideoEntity(
    val documents: List<VideoDocumentEntity>,
    val meta: VideoMetaEntity
)

data class VideoDocumentEntity(
    val thumbnail: String,
    val datetime: Date
)

data class VideoMetaEntity(
    val count : Int,
    val isEnd : Boolean
)