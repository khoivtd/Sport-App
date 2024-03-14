package com.sport.app.feature_event.domain.repository

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MatchRepository {

    fun getAllMatches(): Flow<DataState<List<Match>>>
}