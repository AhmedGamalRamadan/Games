package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformXXX(
    @SerialName("games_count")
    var gamesCount: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("image")
    var image: String? = null,
    @SerialName("image_background")
    var imageBackground: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("slug")
    var slug: String? = null,
    @SerialName("year_end")
    var yearEnd: String? = null,
    @SerialName("year_start")
    var yearStart: Int? = null
)