package com.example.bostatask.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.bostatask.ui.screens.list.model.GameUiModel
import com.example.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class ListViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val gamesCache = mutableMapOf<String, Flow<PagingData<GameUiModel>>>()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getGamesPagingData(slug: String): Flow<PagingData<GameUiModel>> {
        return gamesCache.getOrPut(slug) {
            _searchQuery.flatMapLatest { query ->
                getGamesUseCase(slug)
                    .map { pagingData ->
                        pagingData.map { result ->
                            GameUiModel(
                                id = result.id ?: 0,
                                name = result.name ?: "Unknown",
                                image = result.backgroundImage,
                                rating = result.rating?.toString() ?: "0.0"
                            )
                        }.filter { game ->
                            query.isEmpty() || game.name.contains(query, ignoreCase = true)
                        }
                    }
            }.cachedIn(viewModelScope)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
