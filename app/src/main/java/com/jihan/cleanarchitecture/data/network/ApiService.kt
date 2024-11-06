package com.jihan.cleanarchitecture.data.network

import com.jihan.cleanarchitecture.BuildConfig
import com.jihan.cleanarchitecture.data.model.PixbayDTO
import retrofit2.http.GET
import retrofit2.http.Query

//https://pixabay.com/api/?key=46782721-909d5bb4d604cd8a8eb3ea379&q=flower
const val BASE_URL = "https://pixabay.com"

interface ApiService {

    @GET("api/")
    suspend fun searchImage(
        @Query("key") key: String=BuildConfig.API_KEY,
        @Query("q") query: String,
    ): PixbayDTO


}