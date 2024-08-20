package com.example.toyprojectkakaoapi.presentation.model

import com.example.toyprojectkakaoapi.domain.entity.SearchEntity
import com.example.toyprojectkakaoapi.domain.entity.SearchEntityList

fun SearchEntityList.toModel() : SearchModelList {
    return SearchModelList(
        documents?.map { it.toModel() }
    )
}

fun SearchEntity.toModel() : SearchModel {
    return SearchModel(
        thumbnail, datetime, isLiked
    )
}

fun SearchModel.toEntity(): SearchEntity {
    return SearchEntity(
        thumbnail, datetime
    )
}