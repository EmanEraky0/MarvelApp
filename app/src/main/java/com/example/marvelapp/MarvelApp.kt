package com.example.marvelapp

import android.app.Application
import com.example.marvelapp.constants.appModule
import com.example.marvelapp.feature.details.data.di.detailsModule
import com.example.marvelapp.feature.marvelList.data.di.charModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MarvelApp : Application (){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApp)
            modules(
                appModule,
                charModule,
                detailsModule
            )
        }
    }
}