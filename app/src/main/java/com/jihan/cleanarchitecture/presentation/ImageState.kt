package com.jihan.cleanarchitecture.presentation

import com.jihan.cleanarchitecture.domain.model.ImageModel

data class ImageState(
    val isLoading: Boolean = false,
    val images: List<ImageModel> = emptyList(),
    val error: String? = null
)