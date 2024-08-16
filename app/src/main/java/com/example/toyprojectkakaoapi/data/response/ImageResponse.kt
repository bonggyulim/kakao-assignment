package com.example.toyprojectkakaoapi.data.response

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.Date

data class ImageResponse(
    val documents: List<ImageDocuments>?
)

data class ImageDocuments(
    @SerializedName("thumbnail_url")
    val thumbnail: URL,
    val datetime: Date
)