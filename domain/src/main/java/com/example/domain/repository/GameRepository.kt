package com.example.domain.repository

import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.model.list.GamesResponse

interface GameRepository {
    suspend fun getGamesByGenre(genreSlug: String, page: Int): GamesResponse
    suspend fun getGameDetails(id: Int): GameDetailsResponse
}
