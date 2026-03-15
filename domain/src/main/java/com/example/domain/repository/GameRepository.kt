package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.model.list.Result
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGamesByGenre(genreSlug: String): Flow<PagingData<Result>>
    suspend fun getGameDetails(id: Int): GameDetailsResponse
}
