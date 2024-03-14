package com.sport.app.feature_event.domain.useCases.allTeams

import com.sport.app.feature_event.data.repository.MockTeamRepository
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.useCases.GetAllMatchesInTeam
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllMatchesInTeamTest {

    private lateinit var getAllMatchesInTeam: GetAllMatchesInTeam
    private lateinit var mockTeamRepository: MockTeamRepository

    @Before
    fun setup() {
        mockTeamRepository = MockTeamRepository()
        val matches = listOf(
            Match(
                "2022-04-23T18:00:00.000Z",
                "Team Cool Eagles vs. Team Red Dragons",
                "Team Cool Eagles",
                "Team Red Dragons",
                "Team Red Dragons",
                "https://tstzj.s3.amazonaws.com/highlights.mp4",
                true,
            ),
            Match(
                "2022-04-24T18:00:00.000Z",
                "Team Chill Elephants vs. Team Royal Knights",
                "Team Chill Elephants",
                "Team Royal Knights",
                "Team Chill Elephants",
                "https://tstzj.s3.amazonaws.com/highlights.mp4",
                true,
            ),
            Match(
                "2022-04-24T18:00:00.000Z",
                "Team Win Kings vs. Team Growling Tigers",
                "Team Win Kings",
                "Team Growling Tigers",
                "Team Win Kings",
                "https://tstzj.s3.amazonaws.com/highlights.mp4",
                true,
            ),
            Match(
                "2024-08-13T20:00:00.000Z",
                "Team Cool Eagles vs. Team Serious Lions",
                "Team Cool Eagles",
                "Team Serious Lions",
                null,
                null,
                false,
            ),
            Match(
                "2024-08-13T20:00:00.000Z",
                "Team Angry Pandas vs. Team Win Kings",
                "Team Angry Pandas",
                "Team Win Kings",
                null,
                null,
                false,
            ),
        )
        mockTeamRepository.populateMatchesFromMockDB(matches)
        getAllMatchesInTeam = GetAllMatchesInTeam(mockTeamRepository)
    }

    @Test
    fun getMatchesFromMockRepo_assertMatchesExist() = runBlocking {
        val teamId = "767ec50c-7fdb-4c3d-98f9-d6727ef8252b"
        val matches = getAllMatchesInTeam(teamId).first().data
        assert(matches !== null)
    }

    @Test
    fun getMatchesFromMockRepo_assertMatchesSizeEqualsFive() = runBlocking {
        val teamId = "767ec50c-7fdb-4c3d-98f9-d6727ef8252b"
        val matches = getAllMatchesInTeam(teamId).first().data
        assert(matches !== null && matches.size == 5)
    }

    @Test
    fun getMatchesFromMockRepo_assertMatchHomeFoundInList() = runBlocking {
        val teamId = "767ec50c-7fdb-4c3d-98f9-d6727ef8252b"
        val matches = getAllMatchesInTeam(teamId).first().data
        val matchFoundInList = matches?.find { it.home == "Team Cool Eagles" } != null
        assert(matchFoundInList)
    }

    @Test
    fun getMatchesFromMockRepo_assertLastMatchIsPreviousMatch() = runBlocking {
        val teamId = "767ec50c-7fdb-4c3d-98f9-d6727ef8252b"
        val matches = getAllMatchesInTeam(teamId).first().data
        val isPreviousMatch = matches?.last()?.isPreviousMatch
        assert(isPreviousMatch != null && isPreviousMatch)
    }
}