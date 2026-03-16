package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.list.Result
import com.example.domain.repository.GameRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class GetGamesUseCaseTest {

    private val repository: GameRepository = mockk()
    private val useCase = GetGamesUseCase(repository)

    @Test
    fun `invoke should call getGamesByGenre from repository`() {
        // Given
        val genreSlug = "action"
        val expectedPagingData = PagingData.from(listOf(Result(id = 1, name = "Game 1")))
        every { repository.getGamesByGenre(genreSlug) } returns flowOf(expectedPagingData)

        // When
        val result = useCase(genreSlug)

        // Then
        verify { repository.getGamesByGenre(genreSlug) }
    }
}
