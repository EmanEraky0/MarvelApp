package com.example.marvelapp.feature.marvelList.domain.useCases

import com.example.marvelapp.feature.marvelList.data.repo.CharacterRepoImpl
import com.example.marvelapp.feature.marvelList.domain.models.Data
import com.example.marvelapp.utils.UiResult
import com.example.marvelapp.feature.marvelList.domain.models.Character

class CharacterUseCase(private val repo: CharacterRepoImpl) {
    private var currentOffset = 0
    private var limit = 20
    private var totalItems = -1
    private val characterList = mutableListOf<Character>()

    suspend operator fun invoke(name: String?, ts: String, hash: String): UiResult<Data> {
        return try {
            val result = repo.getAllCharacters(name, ts, hash, limit, currentOffset)
            if (result.results.isEmpty()) {
                UiResult.Error("List is empty")
            } else {
                totalItems = result.total
                characterList.addAll(result.results)
                currentOffset++
                UiResult.Success(result)
            }
        } catch (e: Exception) {
            UiResult.Error(e.message ?: "An unknown error occurred")
        }
    }

    fun canLoadMore(): Boolean =
        characterList.size != totalItems


    fun reset() {
        totalItems = -1
        characterList.clear()
        limit = 20
        currentOffset = 0
    }
}