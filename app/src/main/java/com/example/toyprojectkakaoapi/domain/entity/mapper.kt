package com.example.toyprojectkakaoapi.domain.entity

fun ImageEntity.toSearchEntity() : SearchEntity {
    return SearchEntity(
        documents.map { it.toSearchEntity() },
        meta.toSearchEntity()
    )
}

fun VideoEntity.toSearchEntity() : SearchEntity {
    return SearchEntity(
        documents.map { it.toSearchEntity() },
        meta.toSearchEntity()
    )
}

fun ImageDocumentEntity.toSearchEntity() : SearchDocumentEntity {
    return SearchDocumentEntity(
        thumbnail, datetime
    )
}

fun VideoDocumentEntity.toSearchEntity() : SearchDocumentEntity {
    return SearchDocumentEntity(
        thumbnail, datetime
    )
}

fun ImageMetaEntity.toSearchEntity(): SearchMetaEntity {
    return SearchMetaEntity(
        imageCount = count, imageEnd = isEnd
    )
}
fun VideoMetaEntity.toSearchEntity(): SearchMetaEntity {
    return SearchMetaEntity(
        videoCount = count, videoEnd = isEnd
    )
}
