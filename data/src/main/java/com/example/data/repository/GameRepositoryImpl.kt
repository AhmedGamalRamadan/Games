package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.local.AppDatabase
import com.example.data.local.entity.toDomain
import com.example.data.local.entity.toEntity
import com.example.data.paging.GamesRemoteMediator
import com.example.data.remote.ApiService
import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.model.list.Result
import com.example.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(
    private val api: ApiService,
    private val database: AppDatabase,
    private val apiKey: String
) : GameRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getGamesByGenre(genreSlug: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = GamesRemoteMediator(
                apiService = api,
                database = database,
                genreSlug = genreSlug,
                apiKey = apiKey
            ),
            pagingSourceFactory = {
                database.gameDao().getGamesByGenre(genreSlug)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getGameDetails(id: Int): GameDetailsResponse {
        return try {
            val response = api.getGameDetails(id = id, apiKey = apiKey)
            database.gameDao().insertGameDetails(response.toEntity())
            response
        } catch (e: Exception) {
            val cachedDetails = database.gameDao().getGameDetails(id)
            cachedDetails?.toDomain() ?: throw e
        }
    }
}
