package com.example.toyprojectkakaoapi.data.di

import com.example.toyprojectkakaoapi.data.repository.SearchRepositoryImpl
import com.example.toyprojectkakaoapi.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SearchModule {
    @Binds
    fun bindsSearchRepository(searchRepositoryImpl: SearchRepositoryImpl) : SearchRepository
}