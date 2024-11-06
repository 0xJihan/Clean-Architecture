package com.jihan.cleanarchitecture.data.repository

import com.jihan.cleanarchitecture.common.toImageModel
import com.jihan.cleanarchitecture.data.network.ApiService
import com.jihan.cleanarchitecture.domain.model.ImageModel
import com.jihan.cleanarchitecture.domain.repository.ImageRepository
import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ImageRepository {
    override suspend fun searchImage(query: String): List<ImageModel> {
        return apiService.searchImage(query = query).hits.map { it.toImageModel() }
    }
}