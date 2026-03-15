package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.model.list.GamesResponse
import com.example.domain.repository.GameRepository

class GameRepositoryImpl(
    private val api: ApiService,
    private val apiKey: String
) : GameRepository {
    override suspend fun getGamesByGenre(genreSlug: String, page: Int): GamesResponse {
        return api.getGamesByGenre(genreSlug = genreSlug, apiKey = apiKey, page = page)
    }

    override suspend fun getGameDetails(id: Int): GameDetailsResponse {
        return api.getGameDetails(id = id, apiKey = apiKey)
    }
}
