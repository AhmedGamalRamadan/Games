package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentPlatform(
    @SerialName("platform")
    var platform: PlatformX? = PlatformX()
)