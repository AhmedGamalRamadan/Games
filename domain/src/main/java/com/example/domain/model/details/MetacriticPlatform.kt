package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetacriticPlatform(
    @SerialName("metascore")
    var metascore: Int? = null,
    @SerialName("platform")
    var platform: Platform? = null,
    @SerialName("url")
    var url: String? = null
)