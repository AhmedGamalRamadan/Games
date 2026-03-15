package com.example.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.GameEntity
import com.example.data.local.entity.GameDetailsEntity

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Query("SELECT * FROM games WHERE genreSlug = :genreSlug ORDER BY page ASC, id ASC")
    fun getGamesByGenre(genreSlug: String): PagingSource<Int, GameEntity>

    @Query("DELETE FROM games WHERE genreSlug = :genreSlug")
    suspend fun clearGamesByGenre(genreSlug: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameDetails(details: GameDetailsEntity)

    @Query("SELECT * FROM game_details WHERE id = :id")
    suspend fun getGameDetails(id: Int): GameDetailsEntity?
}
