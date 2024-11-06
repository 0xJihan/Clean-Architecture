package com.jihan.cleanarchitecture.data.di

import com.jihan.cleanarchitecture.data.network.ApiService
import com.jihan.cleanarchitecture.data.network.BASE_URL
import com.jihan.cleanarchitecture.data.repository.ImageRepositoryImpl
import com.jihan.cleanarchitecture.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideImageRepository(apiService: ApiService): ImageRepository {
        return ImageRepositoryImpl(apiService)
    }


}