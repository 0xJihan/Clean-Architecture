package com.jihan.cleanarchitecture.common

import com.jihan.cleanarchitecture.data.model.HitDTO
import com.jihan.cleanarchitecture.domain.model.ImageModel

fun HitDTO.toImageModel(): ImageModel {
    return ImageModel(largeImageURL, type)
}