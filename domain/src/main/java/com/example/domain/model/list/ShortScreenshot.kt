package com.example.domain.model.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShortScreenshot(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("image")
    var image: String? = null
)