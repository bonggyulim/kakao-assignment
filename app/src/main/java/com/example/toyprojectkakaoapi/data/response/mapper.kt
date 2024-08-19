package com.example.toyprojectkakaoapi.data.response

import com.example.toyprojectkakaoapi.domain.entity.ImageEntity
import com.example.toyprojectkakaoapi.domain.entity.ImageEntityList
import com.example.toyprojectkakaoapi.domain.entity.VideoEntity
import com.example.toyprojectkakaoapi.domain.entity.VideoEntityList

fun ImageResponseList.toEntity(): ImageEntityList {
    return ImageEntityList(
        documents?.map { it.toEntity() }
    )
}

fun ImageResponse.toEntity(): ImageEntity {
    return ImageEntity(
        thumbnail, datetime
    )
}

fun VideoResponseList.toEntity(): VideoEntityList {
    return VideoEntityList(
        documents?.map { it.toEntity() }
    )
}

fun VideoResponse.toEntity(): VideoEntity {
    return VideoEntity(
        thumbnail, datetime
    )
}