package com.jihan.cleanarchitecture.domain.repository

import com.jihan.cleanarchitecture.domain.model.ImageModel

interface ImageRepository {

    suspend fun searchImage(query: String): List<ImageModel>

}