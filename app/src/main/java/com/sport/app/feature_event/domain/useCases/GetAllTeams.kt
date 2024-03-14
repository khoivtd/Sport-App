package com.sport.app.feature_event.domain.useCases

import com.sport.app.feature_event.domain.models.Team
import com.sport.app.feature_event.domain.repository.TeamRepository
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTeams @Inject constructor(
    private val repository: TeamRepository
) {
    //by overriding the invoke here, we can call this use case as if it was a function
    operator fun invoke(): Flow<DataState<List<Team>>> {
        return repository.getAllTeams()
    }
}