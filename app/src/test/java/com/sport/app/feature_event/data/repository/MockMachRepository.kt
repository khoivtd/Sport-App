package com.sport.app.feature_event.data.repository

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.repository.MatchRepository
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockMachRepository: MatchRepository {

    private val matches = mutableListOf<Match>()

    override fun getAllMatches(): Flow<DataState<List<Match>>> {
        return flow {
            emit(DataState.Loading(matches))
            if (matches.isNotEmpty()) {
                emit(DataState.Success(matches))
            } else {
                emit(DataState.Error("Match List is empty"))
            }
        }
    }

    fun populateMatchesFromMockDB(matchesFromMockDB: List<Match>) {
        matches.addAll(matchesFromMockDB)
    }
}