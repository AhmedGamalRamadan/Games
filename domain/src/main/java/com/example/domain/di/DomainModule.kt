package com.example.domain.di

import com.example.domain.usecase.GetGameDetailsUseCase
import com.example.domain.usecase.GetGamesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetGameDetailsUseCase(get()) }
    factory { GetGamesUseCase(get()) }
}
