package com.example.domain.model.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    @SerialName("count")
    var count: Int? = 0,
    @SerialName("next")
    var next: String? = "",
    @SerialName("previous")
    var previous: String? = null,
    @SerialName("results")
    var results: List<Result>? = listOf(),
    @SerialName("user_platforms")
    var userPlatforms: Boolean? = false
)