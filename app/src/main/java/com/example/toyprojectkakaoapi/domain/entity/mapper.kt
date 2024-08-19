package com.example.toyprojectkakaoapi.domain.entity

fun ImageEntityList.toSearchEntity() : SearchEntityList {
    return SearchEntityList(
        documents?.map { it.toSearchEntity() }
    )
}

fun VideoEntityList.toSearchEntity() : SearchEntityList {
    return SearchEntityList(
        documents?.map { it.toSearchEntity() }
    )
}

fun ImageEntity.toSearchEntity() : SearchEntity {
    return SearchEntity(
        thumbnail, datetime
    )
}

fun VideoEntity.toSearchEntity() : SearchEntity {
    return SearchEntity(
        thumbnail, datetime
    )
}