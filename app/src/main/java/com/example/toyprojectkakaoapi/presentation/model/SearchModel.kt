package com.example.toyprojectkakaoapi.presentation.model

import java.util.Date

data class SearchModelList(
    val documents: List<SearchModel>?
)
data class SearchModel (
    val thumbnail: String,
    val datetime: Date,
    var isLiked: Boolean
)