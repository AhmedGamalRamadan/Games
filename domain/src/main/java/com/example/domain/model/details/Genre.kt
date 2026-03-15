package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("games_count")
    var gamesCount: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("image_background")
    var imageBackground: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("slug")
    var slug: String? = null
)