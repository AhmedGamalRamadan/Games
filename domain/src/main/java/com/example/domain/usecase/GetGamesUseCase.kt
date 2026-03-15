package com.example.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.domain.model.list.Result
import kotlinx.coroutines.flow.Flow

class GetGamesUseCase(
    private val getPagingSource: (String) -> PagingSource<Int, Result>
) {
    operator fun invoke(genreSlug: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { getPagingSource(genreSlug) }
        ).flow
    }
}
