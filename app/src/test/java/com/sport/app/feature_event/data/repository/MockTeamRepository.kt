package com.sport.app.feature_event.data.repository

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.models.Team
import com.sport.app.feature_event.domain.repository.TeamRepository
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockTeamRepository : TeamRepository {

    private val teams = mutableListOf<Team>()
    private val matches = mutableListOf<Match>()

    override fun getAllTeams(): Flow<DataState<List<Team>>> {
        return flow {
            emit(DataState.Loading(teams))
            if (teams.isNotEmpty()) {
                emit(DataState.Success(teams))
            } else {
                emit(DataState.Error("Team List is empty"))
            }
        }
    }

    override fun getAllMatchesInTeam(teamId: String): Flow<DataState<List<Match>>> {
        return flow {
            emit(DataState.Loading(matches))
            if (matches.isNotEmpty()) {
                emit(DataState.Success(matches))
            } else {
                emit(DataState.Error("Match List in Team is empty"))
            }
        }
    }

    fun populateTeamsFromMockDB(teamsFromMockDB: List<Team>) {
        teams.addAll(teamsFromMockDB)
    }

    fun populateMatchesFromMockDB(matchesFromMockDB: List<Match>) {
        matches.addAll(matchesFromMockDB)
    }
}