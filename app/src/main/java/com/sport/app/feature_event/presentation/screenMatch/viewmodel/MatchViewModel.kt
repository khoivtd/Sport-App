package com.sport.app.feature_event.presentation.screenMatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.app.feature_event.domain.useCases.GetAllMatches
import com.sport.app.feature_event.presentation.screenMatch.state.MatchState
import com.sport.app.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getAllMatchesUseCase: GetAllMatches
): ViewModel() {

    private val _state = MutableStateFlow(MatchState())
    val state = _state.asStateFlow()

    init {
        getAllMatches()
    }

    private fun getAllMatches() {
        getAllMatchesUseCase().onEach { result ->
            when(result) {
                is DataState.Success -> {
                    _state.value = MatchState(matches = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _state.value = MatchState(error = result.errorMessage ?: "Error while fetching teams data.")
                }
                is DataState.Loading -> {
                    _state.value = MatchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}