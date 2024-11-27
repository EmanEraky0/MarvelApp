package com.example.marvelapp.feature.details.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.feature.details.domain.DetailUseCase
import com.example.marvelapp.feature.marvelList.domain.models.Data
import com.example.marvelapp.feature.marvelList.domain.models.Item
import com.example.marvelapp.utils.UiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val detailUseCase: DetailUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<UiResult<Data>?>()
    val uiState: MutableLiveData<UiResult<Data>?> get() = _uiState

    private val _uiUrlState = MutableLiveData<UiResult<Map<String, List<Item>>>>()
    val uiUrlState: MutableLiveData<UiResult<Map<String, List<Item>>>> get() = _uiUrlState


    fun fetchCharacterDetails(charId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.postValue(UiResult.Loading)
                _uiState.postValue(detailUseCase.invoke(charId))
            }
        }
    }


    fun fetchSection(nameSection: String, items: ArrayList<Item>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiUrlState.postValue(UiResult.Loading)
                val res = detailUseCase.fetchDataForSection(items)

                _uiUrlState.postValue(UiResult.Success(mapOf(nameSection to res)))
            }
        }
    }


}