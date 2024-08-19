package com.example.toyprojectkakaoapi.data.response

import java.util.Date

data class VideoResponseList(
    val documents: List<VideoResponse>?
)

data class VideoResponse(
    val thumbnail: String,
    val datetime: Date
)