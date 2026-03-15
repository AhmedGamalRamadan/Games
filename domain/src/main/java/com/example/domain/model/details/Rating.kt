package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("count")
    var count: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("percent")
    var percent: Double? = null,
    @SerialName("title")
    var title: String? = null
)