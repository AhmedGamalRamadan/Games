package com.example.domain.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Store(
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("store")
    var store: StoreX? = StoreX(),
    @SerialName("url")
    var url: String? = ""
)