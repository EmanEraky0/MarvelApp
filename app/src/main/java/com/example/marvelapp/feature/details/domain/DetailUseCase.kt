package com.example.marvelapp.feature.details.domain

import com.example.marvelapp.BuildConfig
import com.example.marvelapp.feature.details.data.repo.DetailsRepoImpl
import com.example.marvelapp.feature.marvelList.domain.models.Character
import com.example.marvelapp.feature.marvelList.domain.models.Data
import com.example.marvelapp.feature.marvelList.domain.models.Item
import com.example.marvelapp.feature.marvelList.domain.models.Section
import com.example.marvelapp.feature.marvelList.domain.models.SectionItem
import com.example.marvelapp.utils.UiResult
import com.example.marvelapp.utils.generateHash

class DetailUseCase(private val repoImpl: DetailsRepoImpl) {
    val ts = System.currentTimeMillis().toString()
    val hash = generateHash(ts, BuildConfig.Private_Key, BuildConfig.Public_Key)
    suspend operator fun invoke(characterId: Int): UiResult<Data> {
        return try {
            val result = repoImpl.getDetailCharacter(characterId, ts, hash)
            if (result.results.isEmpty()) {
                UiResult.Error("Not found character")
            } else {
                UiResult.Success(result)
            }
        } catch (e: Exception) {
            UiResult.Error(e.message ?: "An unknown error occurred")
        }
    }



    suspend fun fetchDataForSection(items: List<Item>): List<Item> {
        val sectionItems = mutableListOf<Item>()

        for (item in items) {
            try {
                val result = repoImpl.getResourceImage(url =item.resourceURI.replace("http", "https"),ts=ts, hash=hash)

                val data = result.results.getOrNull(0)
                if (data?.thumbnail != null) {
                    val itemToAdd = Item(
                        name = data.title,
                        resourceURI = "${data.thumbnail.path}.${data.thumbnail.extension}"
                    )
                    sectionItems.add(itemToAdd)
                }
            } catch (e: Exception) {
                println("Error fetching item for : ${item.resourceURI}, ${e.message}")
            }
        }

        return sectionItems
    }


}