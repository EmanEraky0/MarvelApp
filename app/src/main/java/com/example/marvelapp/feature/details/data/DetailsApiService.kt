package com.example.marvelapp.feature.details.data

import com.example.marvelapp.BuildConfig
import com.example.marvelapp.feature.marvelList.domain.models.AllMarvels
import com.example.marvelapp.feature.marvelList.domain.models.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface DetailsApiService {
    @GET("characters/{characterId}?apikey="+ BuildConfig.Public_Key)
    suspend fun getDetailCharacter(
        @Path("characterId") characterId :Int,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
    ): AllMarvels


    @GET
    suspend fun getResourceImage(
        @Url url: String,
        @Query("apikey") apiKey: String=BuildConfig.Public_Key,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
    ): AllMarvels
}