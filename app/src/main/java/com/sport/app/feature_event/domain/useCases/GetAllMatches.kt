package com.sport.app.feature_event.domain.useCases

import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.repository.MatchRepository
import com.sport.app.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMatches @Inject constructor(
    private val repository: MatchRepository
) {
    //by overriding the invoke here, we can call this use case as if it was a function
    operator fun invoke(): Flow<DataState<List<Match>>> {
        return repository.getAllMatches()
    }
}