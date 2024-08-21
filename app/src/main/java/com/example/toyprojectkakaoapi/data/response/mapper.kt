package com.example.toyprojectkakaoapi.data.response

import com.example.toyprojectkakaoapi.domain.entity.ImageDocumentEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageMetaEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoMetaEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoDocumentEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity

fun ImageResponse.toEntity(): ImageEntity {
    return ImageEntity(
        documents.map { it.toEntity() },
        meta.toEntity()
    )
}

fun ImageDocumentResponse.toEntity(): ImageDocumentEntity {
    return ImageDocumentEntity(
        thumbnail, datetime
    )
}

fun ImageMetaResponse.toEntity(): ImageMetaEntity {
    return ImageMetaEntity(
        count, isEnd
    )
}

fun VideoResponse.toEntity(): VideoEntity {
    return VideoEntity(
        documents.map { it.toEntity() },
        meta.toEntity()
    )
}

fun VideoDocumentResponse.toEntity(): VideoDocumentEntity {
    return VideoDocumentEntity(
        thumbnail, datetime
    )
}

fun VideoMetaResponse.toEntity(): VideoMetaEntity {
    return VideoMetaEntity(
        count, isEnd
    )
}
