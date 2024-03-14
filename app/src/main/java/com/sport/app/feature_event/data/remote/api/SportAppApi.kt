package com.sport.app.feature_event.data.remote.api

import com.sport.app.feature_event.data.remote.dtos.MatchesDto
import com.sport.app.feature_event.data.remote.dtos.TeamsDto
import com.sport.app.utils.Constants.API_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for making API requests.
 */
interface SportAppApi {

    /**
     * Fetches a list of Team dto from the API.
     * @return A response containing a list of Team dtos.
     */
    @GET("teams")
    suspend fun getAllTeams(): TeamsDto

    /**
     * Fetches a list of Match dto from the API.
     * @return A response containing a list of Match dtos.
     */
    @GET("teams/matches")
    suspend fun getAllMatches(): MatchesDto

    /**
     * Fetches a list of Match dto from the API.
     * @return A response containing a list of Match dtos.
     */
    @GET("teams/{teamId}/matches")
    suspend fun getAllMatchesInTeam(@Path("teamId") teamId: String): MatchesDto

}