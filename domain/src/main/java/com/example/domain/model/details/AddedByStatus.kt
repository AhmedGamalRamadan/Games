package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddedByStatus(
    @SerialName("beaten")
    var beaten: Int? = null,
    @SerialName("dropped")
    var dropped: Int? = null,
    @SerialName("owned")
    var owned: Int? = null,
    @SerialName("playing")
    var playing: Int? = null,
    @SerialName("toplay")
    var toplay: Int? = null,
    @SerialName("yet")
    var yet: Int? = null
)