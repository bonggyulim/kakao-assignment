package com.example.toyprojectkakaoapi.data.response

import com.example.toyprojectkakaoapi.domain.entity.ImageDocumentsEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoDocumentEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity

fun ImageResponse.toEntity(): ImageEntity {
    return ImageEntity(
        documents?.map { it.toEntity() }
    )
}

fun ImageDocuments.toEntity(): ImageDocumentsEntity {
    return ImageDocumentsEntity(
        thumbnail, datetime
    )
}

fun VideoResponse.toEntity(): VideoEntity {
    return VideoEntity(
        documents?.map { it.toEntity() }
    )
}

fun VideoDocuments.toEntity(): VideoDocumentEntity {
    return VideoDocumentEntity(
        thumbnail, datetime
    )
}