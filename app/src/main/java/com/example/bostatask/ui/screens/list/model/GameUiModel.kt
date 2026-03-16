package com.example.bostatask.ui.screens.list.model

import androidx.compose.runtime.Immutable

@Immutable
data class GameUiModel(
    val id: Int,
    val name: String,
    val image: String?,
    val rating: String
)
