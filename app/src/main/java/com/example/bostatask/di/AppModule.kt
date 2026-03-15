package com.example.bostatask.di

import com.example.bostatask.ui.screens.details.DetailsViewModel
import com.example.bostatask.ui.screens.list.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}
