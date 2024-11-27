package com.example.marvelapp.feature.marvelList.data.repo

import com.example.marvelapp.feature.marvelList.domain.models.Data

interface CharacterRepo {
    suspend fun getAllCharacters(name:String?,ts: String, hash: String,limit: Int, offset: Int): Data

}