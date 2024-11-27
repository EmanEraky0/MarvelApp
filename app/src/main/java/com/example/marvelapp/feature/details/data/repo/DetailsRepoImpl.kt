package com.example.marvelapp.feature.details.data.repo

import com.example.marvelapp.feature.details.data.dataSource.DetailDataSourceImpl
import com.example.marvelapp.feature.marvelList.domain.models.Data

class DetailsRepoImpl(private val dataSourceImpl: DetailDataSourceImpl) : DetailsRepo {

    override suspend fun getDetailCharacter(characterId:Int, ts:String, hash:String): Data =
        dataSourceImpl.getDetailCharacter(characterId = characterId, ts = ts , hash=hash).data

    override suspend fun getResourceImage(url: String, ts: String, hash: String): Data =
        dataSourceImpl.getResourceImage(url = url, ts = ts , hash=hash).data


}