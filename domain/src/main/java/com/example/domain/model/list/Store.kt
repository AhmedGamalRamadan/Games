package com.example.domain.model.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Store(
    @SerialName("store")
    var store: StoreX? = StoreX()
)