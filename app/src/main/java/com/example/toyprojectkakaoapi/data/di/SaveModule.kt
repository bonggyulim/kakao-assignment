package com.example.toyprojectkakaoapi.data.di

import com.example.toyprojectkakaoapi.data.repository.SaveRepositoryImpl
import com.example.toyprojectkakaoapi.domain.repository.SaveRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SaveModule {
    @Binds
    fun bindsSaveRepository(saveRepositoryImpl: SaveRepositoryImpl) : SaveRepository
}