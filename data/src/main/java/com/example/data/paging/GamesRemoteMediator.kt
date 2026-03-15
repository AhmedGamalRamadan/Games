package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.local.AppDatabase
import com.example.data.local.entity.GameEntity
import com.example.data.local.entity.RemoteKeyEntity
import com.example.data.remote.ApiService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GamesRemoteMediator(
    private val apiService: ApiService,
    private val database: AppDatabase,
    private val genreSlug: String,
    private val apiKey: String
) : RemoteMediator<Int, GameEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val response = apiService.getGamesByGenre(
                genreSlug = genreSlug,
                apiKey = apiKey,
                page = page
            )

            val games = response.results ?: emptyList()
            val endOfPaginationReached = games.isEmpty() || response.next == null

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.gameDao().clearGamesByGenre(genreSlug)
                    database.remoteKeyDao().clearRemoteKeysByGenre(genreSlug)
                }

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                val keys = games.map {
                    RemoteKeyEntity(gameId = it.id ?: 0, genreSlug = genreSlug, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeyDao().insertAll(keys)

                val entities = games.map {
                    GameEntity(
                        id = it.id ?: 0,
                        name = it.name ?: "",
                        backgroundImage = it.backgroundImage,
                        rating = it.rating,
                        genreSlug = genreSlug,
                        page = page
                    )
                }
                database.gameDao().insertAll(entities)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, GameEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { game ->
                database.remoteKeyDao().getRemoteKeyByIdAndGenre(game.id, genreSlug)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, GameEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { game ->
                database.remoteKeyDao().getRemoteKeyByIdAndGenre(game.id, genreSlug)
            }
    }

    private suspend fun getRemoteKeyClosestToPosition(state: PagingState<Int, GameEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeyDao().getRemoteKeyByIdAndGenre(id, genreSlug)
            }
        }
    }
}
