package com.example.domain.model.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformX(
    @SerialName("platform")
    var platform: Platform? = Platform()
)