package com.sport.app.feature_event.domain.useCases.allTeams

import com.sport.app.feature_event.data.repository.MockMachRepository
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.useCases.GetAllMatches
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllMatchesTest {

    private lateinit var getAllMatches: GetAllMatches
    private lateinit var mockMachRepository: MockMachRepository

    @Before
    fun setup() {
        mockMachRepository = MockMachRepository()
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
        mockMachRepository.populateMatchesFromMockDB(matches)
        getAllMatches = GetAllMatches(mockMachRepository)
    }

    @Test
    fun getMatchesFromMockRepo_assertMatchesExist() = runBlocking {
        val matches = getAllMatches().first().data
        assert(matches !== null)
    }

    @Test
    fun getMatchesFromMockRepo_assertSizeEqualsFive() = runBlocking {
        val matches = getAllMatches().first().data
        assert(matches !== null && matches.size == 5)
    }

    @Test
    fun getMatchesFromMockRepo_assertMatchFoundInList() = runBlocking {
        val matches = getAllMatches().first().data
        val matchHomeFoundInList = matches?.find { it.home == "Team Chill Elephants" } != null
        assert(matchHomeFoundInList)
    }

    @Test
    fun getMatchesFromMockRepo_assertLastMatchIsPreviousMatch() = runBlocking {
        val matches = getAllMatches().first().data
        val isPreviousMatch = matches?.last()?.isPreviousMatch
        assert(isPreviousMatch != null && isPreviousMatch)
    }
}