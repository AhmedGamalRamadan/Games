package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.list.Result
import com.example.domain.repository.GameRepository

class GamesPagingSource(
    private val repository: GameRepository,
    private val genreSlug: String
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = repository.getGamesByGenre(genreSlug, page)
            val games = response.results ?: emptyList()

            LoadResult.Page(
                data = games,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.next != null) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
