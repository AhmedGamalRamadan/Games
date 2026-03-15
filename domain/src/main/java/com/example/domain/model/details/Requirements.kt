package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Requirements(
    @SerialName("minimum")
    var minimum: String? = null,
    @SerialName("recommended")
    var recommended: String? = null
)