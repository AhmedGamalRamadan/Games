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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ListViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _selectedSlug = MutableStateFlow("adventure")
    val selectedSlug = _selectedSlug.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val gamesPagingData: StateFlow<PagingData<GameUiModel>> = combine(
        _selectedSlug,
        _searchQuery
    ) { slug, query ->
        slug to query
    }.flatMapLatest { (slug, query) ->
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
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun onSlugSelected(slug: String) {
        if (_selectedSlug.value != slug) {
            _selectedSlug.value = slug
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
