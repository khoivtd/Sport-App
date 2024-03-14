package com.sport.app.feature_event.presentation.screenMatch.state

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.utils.Constants.EMPTY_STRING

data class MatchState(
    val isLoading: Boolean = false,
    val matches: List<Match> = emptyList(),
    val error: String = EMPTY_STRING
)
