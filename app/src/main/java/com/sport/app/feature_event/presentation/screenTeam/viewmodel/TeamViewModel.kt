package com.sport.app.feature_event.presentation.screenTeam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.app.feature_event.domain.useCases.GetAllTeams
import com.sport.app.feature_event.presentation.screenTeam.state.TeamState
import com.sport.app.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getAllTeamsUseCase: GetAllTeams,
): ViewModel() {

    private val _state = MutableStateFlow(TeamState())
    val state = _state.asStateFlow()

    init {
        getAllTeams()
    }

    private fun getAllTeams() {
        getAllTeamsUseCase().onEach { result ->
            when(result) {
                is DataState.Success -> {
                    _state.value = TeamState(teams = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _state.value = TeamState(error = result.errorMessage ?: "Error while fetching teams data.")
                }
                is DataState.Loading -> {
                    _state.value = TeamState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}