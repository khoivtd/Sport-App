package com.sport.app.feature_event.domain.repository

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.models.Team
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun getAllTeams(): Flow<DataState<List<Team>>>

    fun getAllMatchesInTeam(teamId: String): Flow<DataState<List<Match>>>
}