package com.sport.app.feature_event.data.repository

import android.util.Log
import com.sport.app.feature_event.data.cache.db.SportAppDao
import com.sport.app.feature_event.data.cache.entity.TeamMatchesEntity
import com.sport.app.feature_event.data.remote.api.SportAppApi
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.models.Team
import com.sport.app.feature_event.domain.repository.TeamRepository
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: SportAppApi, private val dao: SportAppDao
) : TeamRepository {

    override fun getAllTeams(): Flow<DataState<List<Team>>> = flow {
        emit(DataState.Loading())

        val teams = dao.getTeamEntities().map { it.toTeam() }
        emit(DataState.Loading(data = teams))

        Log.e("DEBUG", "getAllTeams teams = $teams")

        if (teams.isEmpty()) {
            try {
                val teamDtoList = api.getAllTeams().teams
                Log.e("DEBUG", "getAllTeams teamDtoList = $teamDtoList")
                dao.insertTeamEntities(teamDtoList.map { it.toTeamEntity() })
            } catch (e: HttpException) {
                emit(DataState.Error(errorMessage = e.message, data = teams))
            } catch (e: IOException) {
                emit(DataState.Error(errorMessage = e.message, data = teams))
            }

            val newTeams = dao.getTeamEntities().map { it.toTeam() }
            Log.e("DEBUG", "getAllTeams newTeams = $newTeams")
            emit(DataState.Success(newTeams))
        } else {
            emit(DataState.Success(teams))
        }
    }

    override fun getAllMatchesInTeam(teamId: String): Flow<DataState<List<Match>>> = flow {
        Log.e("DEBUG", "getAllMatchesInTeam teamId = $teamId")
        emit(DataState.Loading())

        val allMatches = dao.getTeamMatchesEntities(teamId)
        Log.e("DEBUG", "getAllMatchesInTeam allMatches = $allMatches")

            if (allMatches == null) {
                try {
                    val matchesDto = api.getAllMatchesInTeam(teamId).matches
                    Log.e("DEBUG", "getAllMatchesInTeam matchesDto = $matchesDto")

                    var matches = TeamMatchesEntity(
                        teamId, matchesDto.previousMatches.map { it.toMatchEntity(true).toMatch() }
                    )
                    Log.e("DEBUG", "getAllMatchesInTeam previousMatches = $matches")
                    Log.e("DEBUG", "getAllMatchesInTeam previousMatches id = ${matches.id}")
                    Log.e("DEBUG", "getAllMatchesInTeam previousMatches size = ${matches.matches.size}")
                    dao.insertTeamMatchesEntity(matches)

                    matches = TeamMatchesEntity(
                        teamId, matchesDto.upcomingMatches.map { it.toMatchEntity().toMatch() }
                    )
                    Log.e("DEBUG", "getAllMatchesInTeam upcomingMatches = $matches")
                    Log.e("DEBUG", "getAllMatchesInTeam upcomingMatches id = ${matches.id}")
                    Log.e("DEBUG", "getAllMatchesInTeam upcomingMatches size = ${matches.matches.size}")
                    dao.insertTeamMatchesEntity(matches)

                    val newMatches = dao.getTeamMatchesEntities(teamId).toAllMatches()
                    Log.e("DEBUG", "getAllMatchesInTeam newMatches = $newMatches")
                    Log.e("DEBUG", "getAllMatchesInTeam newMatches id = ${newMatches.id}")
                    Log.e("DEBUG", "getAllMatchesInTeam newMatches size = ${newMatches.matches.size}")
                    emit(DataState.Success(newMatches.matches))
                } catch (e: HttpException) {
                    emit(DataState.Error(errorMessage = e.message, data = allMatches.matches))
                } catch (e: IOException) {
                    emit(DataState.Error(errorMessage = e.message, data = allMatches.matches))
                }
            } else {
                emit(DataState.Success(allMatches.matches))
            }
    }
}