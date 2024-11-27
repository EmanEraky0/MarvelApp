package com.example.marvelapp.feature.marvelList.data.di

import com.example.marvelapp.feature.marvelList.data.ApiService
import com.example.marvelapp.feature.marvelList.data.dataSource.DataSourceImpl
import com.example.marvelapp.feature.marvelList.data.repo.CharacterRepoImpl
import com.example.marvelapp.feature.marvelList.domain.useCases.CharacterUseCase
import com.example.marvelapp.feature.marvelList.viewModels.MarvelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val charModule = module {
    single { DataSourceImpl(get()) }
    single { CharacterRepoImpl(get()) }
    single { getApiService(get()) }
    single { CharacterUseCase(get()) }

    viewModel {
        MarvelViewModel(get())
    }

}


private fun getApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)