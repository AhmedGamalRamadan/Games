package com.example.domain.model.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EsrbRating(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("name_en")
    var nameEn: String? = null,
    @SerialName("name_ru")
    var nameRu: String? = null,
    @SerialName("slug")
    var slug: String? = null
)