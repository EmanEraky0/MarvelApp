package com.example.marvelapp.feature.marvelList.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.BuildConfig
import com.example.marvelapp.feature.marvelList.domain.models.Data
import com.example.marvelapp.feature.marvelList.domain.useCases.CharacterUseCase
import com.example.marvelapp.utils.UiResult
import com.example.marvelapp.utils.generateHash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelViewModel(private val useCase: CharacterUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiResult<Data>?>()
    val uiState: MutableLiveData<UiResult<Data>?> get() = _uiState


    fun fetchCharacters(name: String?) {
        val ts = System.currentTimeMillis().toString()
        val hash = generateHash(ts, BuildConfig.Private_Key, BuildConfig.Public_Key)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (useCase.canLoadMore()) {
                    _uiState.postValue(UiResult.Loading)
                    _uiState.postValue(useCase.invoke(name, ts, hash))
                }

            }
        }
    }

    fun reset(){
        useCase.reset()
    }

    fun clearUiState() {
        _uiState.value = null
    }
}