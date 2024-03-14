package com.sport.app.feature_event.presentation.screenTeam.state

import com.sport.app.feature_event.domain.models.Team
import com.sport.app.utils.Constants.EMPTY_STRING

data class TeamState(
    val isLoading: Boolean = false,
    val teams: List<Team> = emptyList(),
    val error: String = EMPTY_STRING
)
