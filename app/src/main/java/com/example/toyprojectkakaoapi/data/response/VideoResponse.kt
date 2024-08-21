package com.example.toyprojectkakaoapi.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class VideoResponse(
    val documents: List<VideoDocumentResponse>,
    val meta: VideoMetaResponse
)

data class VideoDocumentResponse(
    val thumbnail: String,
    val datetime: Date
)

data class VideoMetaResponse(
    @SerializedName("pageable_count")
    val count : Int,
    @SerializedName("is_end")
    val isEnd : Boolean
)