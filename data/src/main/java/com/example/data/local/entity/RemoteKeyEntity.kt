package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val gameId: Int,
    val genreSlug: String,
    val prevKey: Int?,
    val nextKey: Int?
)
