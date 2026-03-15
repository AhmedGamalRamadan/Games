package com.example.bostatask.ui.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    object ListScreen

    @Serializable
    data class DetailsScreen(val gameId: Int)
}
