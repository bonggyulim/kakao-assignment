package com.example.toyprojectkakaoapi.presentation.model

import java.util.Date

data class SearchModel(
    val documents: List<SearchDocumentModel>,
    val meta : SearchMetaModel
)
data class SearchDocumentModel (
    val thumbnail: String,
    val datetime: Date,
    var isLiked: Boolean
)

data class SearchMetaModel(
    val imageCount : Int,
    val imageEnd : Boolean,
    val videoCount : Int,
    val videoEnd : Boolean
)