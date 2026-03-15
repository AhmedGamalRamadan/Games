package com.example.domain.di

import com.example.domain.usecase.GetGameDetailsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetGameDetailsUseCase(get()) }
}
