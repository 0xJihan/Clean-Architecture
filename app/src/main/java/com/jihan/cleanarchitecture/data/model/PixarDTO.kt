package com.jihan.cleanarchitecture.data.model

data class PixbayDTO(
    val total: Int,
    val totalHits: Int,
    val hits: List<HitDTO>
)