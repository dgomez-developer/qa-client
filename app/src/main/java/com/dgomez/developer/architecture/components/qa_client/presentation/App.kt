package com.dgomez.developer.architecture.components.qa_client.presentation

import android.app.Application
import com.dgomez.developer.architecture.components.qa_client.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidLogger()

            androidContext(this@App)

            modules(
                listOf(
                persistenceModule, retrofitModule, graphql,
                apiModule, repositoryModule, interactorModule,
                viewModelModule
            ))
        }

    }
}