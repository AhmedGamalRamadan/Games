package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.details.GameDetailsResponse

@Entity(tableName = "game_details")
data class GameDetailsEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val released: String?,
    val descriptionRaw: String?,
    val clip: String? // Store as string for simplicity
)

fun GameDetailsEntity.toDomain() = GameDetailsResponse(
    id = id,
    name = name,
    backgroundImage = backgroundImage,
    rating = rating,
    released = released,
    descriptionRaw = descriptionRaw,
    clip = clip
)

fun GameDetailsResponse.toEntity() = GameDetailsEntity(
    id = id ?: 0,
    name = name,
    backgroundImage = backgroundImage,
    rating = rating,
    released = released,
    descriptionRaw = descriptionRaw,
    clip = clip?.toString()
)
