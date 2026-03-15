package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    @SerialName("name")
    var name: String? = null,
    @SerialName("platform")
    var platform: Int? = null,
    @SerialName("slug")
    var slug: String? = null
)