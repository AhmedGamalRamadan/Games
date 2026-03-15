package com.example.domain.usecase

import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.repository.GameRepository
import com.example.domain.utils.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGameDetailsUseCase(private val repository: GameRepository) {
    operator fun invoke(id: Int): Flow<ApiState<GameDetailsResponse>> = flow {
        emit(ApiState.Loading)
        try {
            val response = repository.getGameDetails(id)
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message ?: "An unknown error occurred"))
        }
    }
}
