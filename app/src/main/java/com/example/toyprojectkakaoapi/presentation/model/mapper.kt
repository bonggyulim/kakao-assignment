package com.example.toyprojectkakaoapi.presentation.model

import com.example.toyprojectkakaoapi.domain.entity.SearchMetaEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchDocumentEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchEntity

fun SearchEntity.toModel() : SearchModel {
    return SearchModel(
        documents.map { it.toModel() },
        meta.toModel()
    )
}

fun SearchMetaEntity.toModel(): SearchMetaModel {
    return SearchMetaModel(
        imageCount, imageEnd, videoCount, videoEnd
    )
}

fun SearchDocumentEntity.toModel() : SearchDocumentModel {
    return SearchDocumentModel(
        thumbnail, datetime, isLiked
    )
}

fun SearchDocumentModel.toEntity(): SearchDocumentEntity {
    return SearchDocumentEntity(
        thumbnail, datetime
    )
}