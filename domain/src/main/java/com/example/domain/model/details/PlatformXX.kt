package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformXX(
    @SerialName("platform")
    var platform: PlatformXXX? = PlatformXXX(),
    @SerialName("released_at")
    var releasedAt: String? = "",
    @SerialName("requirements")
    var requirements: Requirements? = Requirements()
)