package com.example.toyprojectkakaoapi.presentation.model

import com.example.toyprojectkakaoapi.domain.entity.ImageDocumentsEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoDocumentEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity

fun ImageEntity.toModel() : ImageModel {
    return ImageModel(
        documents?.map { it.toModel() }
    )
}

fun ImageDocumentsEntity.toModel() : ImageDocumentsModel {
    return ImageDocumentsModel(
        thumbnail, datetime
    )
}

fun VideoEntity.toModel() : VideoModel {
    return VideoModel(
        documents?.map { it.toModel() }
    )
}

fun VideoDocumentEntity.toModel() : VideoDocumentsModel {
    return VideoDocumentsModel(
        thumbnail, datetime
    )
}