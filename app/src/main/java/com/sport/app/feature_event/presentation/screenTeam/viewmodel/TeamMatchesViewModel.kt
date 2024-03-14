package com.sport.app.feature_event.presentation.screenTeam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sport.app.feature_event.domain.useCases.GetAllMatchesInTeam
import com.sport.app.feature_event.presentation.screenTeam.state.TeamMatchesState
import com.sport.app.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamMatchesViewModel @Inject constructor(
    private val getAllMatchesInTeamUseCase: GetAllMatchesInTeam,
) : ViewModel() {

    private val _state = MutableStateFlow(TeamMatchesState())
    val state = _state.asStateFlow()

    fun getAllMatchesInTeam(teamId: String) {
        getAllMatchesInTeamUseCase(teamId).onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _state.value = TeamMatchesState(matches = result.data ?: emptyList())
                }

                is DataState.Error -> {
                    _state.value = TeamMatchesState(
                        error = result.errorMessage ?: "Error while fetching teams data."
                    )
                }

                is DataState.Loading -> {
                    _state.value = TeamMatchesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}