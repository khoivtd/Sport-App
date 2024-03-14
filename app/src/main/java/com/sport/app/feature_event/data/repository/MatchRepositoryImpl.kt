package com.sport.app.feature_event.data.repository

import com.sport.app.feature_event.data.cache.db.SportAppDao
import com.sport.app.feature_event.data.remote.api.SportAppApi
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.repository.MatchRepository
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val api: SportAppApi, private val dao: SportAppDao
) : MatchRepository {

    override fun getAllMatches(): Flow<DataState<List<Match>>> = flow {
        emit(DataState.Loading())

        val matches = dao.getMatchEntities().map { it.toMatch() }
        emit(DataState.Loading(data = matches))

        if (matches.isEmpty()) {
            try {
                val matchesDto = api.getAllMatches().matches

                dao.insertMatchesEntities(matchesDto.previousMatches.map { it.toMatchEntity(true) })
                dao.insertMatchesEntities(matchesDto.upcomingMatches.map { it.toMatchEntity() })
            } catch (e: HttpException) {
                emit(DataState.Error(errorMessage = e.message, data = matches))
            } catch (e: IOException) {
                emit(DataState.Error(errorMessage = e.message, data = matches))
            }

            val newMatches = dao.getMatchEntities().map { it.toMatch() }
            emit(DataState.Success(newMatches))
        } else {
            emit(DataState.Success(matches))
        }
    }
}
