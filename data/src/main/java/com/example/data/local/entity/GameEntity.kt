package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.list.Result

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val backgroundImage: String?,
    val rating: Double?,
    val genreSlug: String,
    val page: Int
)

fun GameEntity.toDomain() = Result(
    id = id,
    name = name,
    backgroundImage = backgroundImage,
    rating = rating
)
