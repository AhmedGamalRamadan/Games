package com.example.bostatask.ui.screens.list

import app.cash.turbine.test
import com.example.domain.usecase.GetGamesUseCase
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    private val getGamesUseCase: GetGamesUseCase = mockk()
    private lateinit var viewModel: ListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ListViewModel(getGamesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onSearchQueryChanged should update searchQuery flow`() = runTest {
        val query = "The Witcher"
        
        viewModel.searchQuery.test {
            assertEquals("", awaitItem())
            viewModel.onSearchQueryChanged(query)
            assertEquals(query, awaitItem())
        }
    }
}
