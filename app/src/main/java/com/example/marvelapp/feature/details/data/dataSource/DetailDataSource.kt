package com.example.marvelapp.feature.details.data.dataSource

import com.example.marvelapp.feature.marvelList.domain.models.AllMarvels


interface DetailDataSource {
    suspend fun getDetailCharacter(characterId:Int, ts:String, hash:String): AllMarvels
    suspend fun getResourceImage(url:String, ts:String, hash:String): AllMarvels

}