package com.jihan.cleanarchitecture.domain.di

import com.jihan.cleanarchitecture.domain.repository.ImageRepository
import com.jihan.cleanarchitecture.domain.use_cases.SearchImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideSearchImageUseCase(imageRepository: ImageRepository): SearchImageUseCase {
        return SearchImageUseCase(imageRepository)
    }

}