package com.example.marvelapp.feature.marvelList.data.repo

import com.example.marvelapp.feature.marvelList.data.dataSource.DataSourceImpl
import com.example.marvelapp.feature.marvelList.domain.models.Data

class CharacterRepoImpl(private val dataSourceImpl: DataSourceImpl) : CharacterRepo {

    override suspend fun getAllCharacters(name: String?,ts: String, hash: String,limit: Int, offset: Int): Data =
        dataSourceImpl.getAllCharacters(name = name, ts = ts , hash=hash ,limit =limit, offset=offset).data

}