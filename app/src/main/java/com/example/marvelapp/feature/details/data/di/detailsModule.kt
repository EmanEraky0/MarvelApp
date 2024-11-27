package com.example.marvelapp.feature.details.data.di

import com.example.marvelapp.feature.details.data.DetailsApiService
import com.example.marvelapp.feature.details.data.dataSource.DetailDataSourceImpl
import com.example.marvelapp.feature.details.data.repo.DetailsRepoImpl
import com.example.marvelapp.feature.details.domain.DetailUseCase
import com.example.marvelapp.feature.details.viewModels.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsModule = module {
    single { DetailDataSourceImpl(get()) }
    single { DetailsRepoImpl(get()) }
    single { getApiService(get()) }
    single { DetailUseCase(get()) }

    viewModel {
        DetailsViewModel(get())
    }

}


private fun getApiService(retrofit: Retrofit): DetailsApiService =
    retrofit.create(DetailsApiService::class.java)