package com.example.bostatask.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.list.Result
import com.example.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ListViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _selectedSlug = MutableStateFlow("action")
    val selectedSlug = _selectedSlug.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _gamesPagingData = MutableStateFlow<PagingData<Result>>(PagingData.empty())
    val gamesPagingData: StateFlow<PagingData<Result>> = _gamesPagingData.asStateFlow()

    init {
        loadGames("action")
    }

    fun onSlugSelected(slug: String) {
        if (_selectedSlug.value != slug) {
            _selectedSlug.value = slug
            loadGames(slug)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun loadGames(slug: String) {
        viewModelScope.launch {
            getGamesUseCase(slug)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _gamesPagingData.value = it
                }
        }
    }
}
