package com.example.toyprojectkakaoapi.presentation.model

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.Date

data class ImageModel (
    val documents: List<ImageDocumentsModel>?
)

data class ImageDocumentsModel(
    val thumbnail: URL,
    val datetime: Date
)