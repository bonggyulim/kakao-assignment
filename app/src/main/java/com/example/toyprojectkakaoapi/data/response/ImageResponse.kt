package com.example.toyprojectkakaoapi.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ImageResponse(
    val documents: List<ImageDocumentResponse>,
    val meta: ImageMetaResponse
)

data class ImageDocumentResponse(
    @SerializedName("thumbnail_url")
    val thumbnail: String,
    val datetime: Date
)

data class ImageMetaResponse(
    @SerializedName("pageable_count")
    val count : Int,
    @SerializedName("is_end")
    val isEnd : Boolean
)