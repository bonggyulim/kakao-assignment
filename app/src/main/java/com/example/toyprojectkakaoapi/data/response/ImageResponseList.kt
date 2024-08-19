package com.example.toyprojectkakaoapi.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ImageResponseList(
    val documents: List<ImageResponse>?
)

data class ImageResponse(
    @SerializedName("thumbnail_url")
    val thumbnail: String,
    val datetime: Date
)