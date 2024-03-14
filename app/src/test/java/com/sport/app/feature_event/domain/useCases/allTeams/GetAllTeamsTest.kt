package com.sport.app.feature_event.domain.useCases.allTeams

import com.sport.app.feature_event.data.repository.MockTeamRepository
import com.sport.app.feature_event.domain.models.Team
import com.sport.app.feature_event.domain.useCases.GetAllTeams
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllTeamsTest {

    private lateinit var getAllTeams: GetAllTeams
    private lateinit var mockTeamRepository: MockTeamRepository

    @Before
    fun setup() {
        mockTeamRepository = MockTeamRepository()
        val teams = listOf(
            Team(
                "767ec50c-7fdb-4c3d-98f9-d6727ef8252b",
                "Team Red Dragons",
                "https://tstzj.s3.amazonaws.com/dragons.png",
            ),
            Team(
                "7b4d8114-742b-4410-971a-500162101158",
                "Team Cool Eagles",
                "https://tstzj.s3.amazonaws.com/eagle.png",
            ),
            Team(
                "efb06ca2-78bc-448e-bda5-a6af9eccdcd0",
                "Team Chill Elephants",
                "https://tstzj.s3.amazonaws.com/elephant.png",
            ),
            Team(
                "01490dfe-0bc7-42ad-b471-2fba2b9b8f5e",
                "Team Win Kings",
                "https://tstzj.s3.amazonaws.com/kings.png",
            ),
            Team(
                "849a0c56-eb8b-11ec-8ea0-0242ac120002",
                "Team Serious Lions",
                "https://tstzj.s3.amazonaws.com/lion.png"
            ),
        )
        mockTeamRepository.populateTeamsFromMockDB(teams)
        getAllTeams = GetAllTeams(mockTeamRepository)
    }

    @Test
    fun getTeamsFromMockRepo_assertTeamsExist() = runBlocking {
        val teams = getAllTeams().first().data
        assert(teams !== null)
    }

    @Test
    fun getTeamsFromMockRepo_assertSizeEqualsFive() = runBlocking {
        val teams = getAllTeams().first().data
        assert(teams !== null && teams.size == 5)
    }

    @Test
    fun getTeamsFromMockRepo_assertTeamIdFoundInList() = runBlocking {
        val teams = getAllTeams().first().data
        val teamIdFoundInList =
            teams?.find { it.id == "849a0c56-eb8b-11ec-8ea0-0242ac120002" } != null
        assert(teamIdFoundInList)
    }
}