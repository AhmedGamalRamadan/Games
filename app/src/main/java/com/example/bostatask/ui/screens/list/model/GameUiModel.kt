package com.example.bostatask.ui.screens.list.model

import androidx.compose.runtime.Immutable

@Immutable
data class GameUiModel(
    val id: Int = 0,
    val name: String = "",
    val image: String? = "",
    val rating: Double = 0.0
)
