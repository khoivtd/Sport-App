package com.sport.app.feature_event.presentation.screenTeam.state

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.utils.Constants

data class TeamMatchesState(
    val isLoading: Boolean = false,
    val matches: List<Match> = emptyList(),
    val error: String = Constants.EMPTY_STRING
)
