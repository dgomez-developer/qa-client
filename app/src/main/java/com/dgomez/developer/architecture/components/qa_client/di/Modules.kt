package com.dgomez.developer.architecture.components.qa_client.di

import androidx.room.Room
import com.dgomez.developer.architecture.components.qa_client.data.api.QuestionsApi
import com.dgomez.developer.architecture.components.qa_client.data.database.AppDatabase
import com.dgomez.developer.architecture.components.qa_client.data.executor.BackgroundExecutor
import com.dgomez.developer.architecture.components.qa_client.data.repository.QuestionsRepositoryImpl
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsLocalDataSource
import com.dgomez.developer.architecture.components.qa_client.data.repository.datasource.QuestionsNetworkDataSource
import com.dgomez.developer.architecture.components.qa_client.domain.executor.PostExecutionThread
import com.dgomez.developer.architecture.components.qa_client.domain.executor.ThreadExecutor
import com.dgomez.developer.architecture.components.qa_client.domain.interactor.GetQuestionsUseCase
import com.dgomez.developer.architecture.components.qa_client.domain.repository.QuestionsRepository
import com.dgomez.developer.architecture.components.qa_client.presentation.executor.UiThread
import com.dgomez.developer.architecture.components.qa_client.presentation.presenter.QuestionsPresenter
import com.dgomez.developer.architecture.components.qa_client.presentation.presenter.QuestionsPresenterImpl
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "app-database"
        ).build()
    }
}

val retrofitModule = module {

    fun provideJackson(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(KotlinModule())
        }
    }

    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun provideRetrofit(mapper: ObjectMapper, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://qa-api-server.herokuapp.com/")
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .client(httpClient)
            .build()
    }

    single { provideJackson() }

    single { provideHttpClient() }

    single { provideRetrofit(get(), get()) }

}

val apiModule = module {

    fun provideQuestionsApi(retrofit: Retrofit): QuestionsApi {
        return retrofit.create(QuestionsApi::class.java)
    }

    single { provideQuestionsApi(get()) }

}

val repositoryModule = module {

    single<QuestionsRepository> { QuestionsRepositoryImpl(get(), get()) }

    single { QuestionsLocalDataSource(get()) }

    single { QuestionsNetworkDataSource(get()) }
}

val interactorModule = module {

    factory { GetQuestionsUseCase(get(), get(), get()) }

    single<ThreadExecutor> { BackgroundExecutor() }

    single<PostExecutionThread> { UiThread() }

}

val presenterModule = module {

    factory<QuestionsPresenter> { QuestionsPresenterImpl(get()) }
}












