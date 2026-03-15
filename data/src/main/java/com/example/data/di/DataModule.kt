package com.example.data.di

import androidx.room.Room
import com.example.data.BuildConfig
import com.example.data.local.AppDatabase
import com.example.data.remote.ApiService
import com.example.data.repository.GameRepositoryImpl
import com.example.domain.repository.GameRepository
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

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

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "games_db"
        ).fallbackToDestructiveMigration().build()
    }

    single<GameRepository> { GameRepositoryImpl(get(), get(), BuildConfig.API_KEY) }
}
