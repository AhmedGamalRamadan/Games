package com.example.bostatask.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.details.GameDetailsResponse
import com.example.domain.usecase.GetGameDetailsUseCase
import com.example.domain.utils.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
) : ViewModel() {

    private val _gameDetailsState = MutableStateFlow<ApiState<GameDetailsResponse>>(ApiState.Loading)
    val gameDetailsState = _gameDetailsState.asStateFlow()

    fun loadGameDetails(id: Int) {
        viewModelScope.launch {
            getGameDetailsUseCase(id).collectLatest { state ->
                _gameDetailsState.emit(state)
            }
        }
    }
}
