package com.example.toyprojectkakaoapi.presentation.model

import java.net.URL
import java.util.Date

data class VideoModel(
    val documents: List<VideoDocumentsModel>?
)

data class VideoDocumentsModel(
    val thumbnail: URL,
    val datetime: Date
)