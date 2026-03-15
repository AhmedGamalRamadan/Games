package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.list.Result
import com.example.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GetGamesUseCase(private val repository: GameRepository) {
    operator fun invoke(genreSlug: String): Flow<PagingData<Result>> {
        return repository.getGamesByGenre(genreSlug)
    }
}
