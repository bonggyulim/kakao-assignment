package com.example.toyprojectkakaoapi.data.response

import java.net.URL
import java.util.Date

data class VideoResponse(
    val documents: List<VideoDocuments>?
)

data class VideoDocuments(
    val thumbnail: URL,
    val datetime: Date
)