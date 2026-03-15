package com.example.data.di

import com.example.data.paging.GamesPagingSource
import com.example.data.remote.ApiService
import com.example.data.repository.GameRepositoryImpl
import com.example.domain.repository.GameRepository
import com.example.domain.usecase.GetGamesUseCase
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val dataModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .client(get())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }

    single<GameRepository> { GameRepositoryImpl(get(), "5fe005cd104f42e2bbba7a59936da872") }

    factory {
        GetGamesUseCase { genreSlug -> GamesPagingSource(get(), genreSlug) }
    }
}
