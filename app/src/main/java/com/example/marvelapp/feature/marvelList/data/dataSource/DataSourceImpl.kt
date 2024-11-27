package com.example.marvelapp.feature.marvelList.data.dataSource

import com.example.marvelapp.feature.marvelList.data.ApiService
import com.example.marvelapp.feature.marvelList.domain.models.AllMarvels

class DataSourceImpl(private val apiService: ApiService) : ModuleDataSource {
    override suspend fun getAllCharacters(name: String?, ts: String, hash: String, limit: Int, offset: Int): AllMarvels =
        if (name != null)
            apiService.getAllCharacters(name =name, ts = ts, hash = hash, limit = limit, offset = offset)
        else
            apiService.getAllCharacters(ts = ts, hash = hash, limit = limit, offset = offset)

}