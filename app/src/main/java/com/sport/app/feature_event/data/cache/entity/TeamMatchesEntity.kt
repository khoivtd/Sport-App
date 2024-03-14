package com.sport.app.feature_event.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sport.app.feature_event.domain.models.AllMatches
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.utils.Constants

@Entity
data class TeamMatchesEntity(
    @PrimaryKey var id: String = Constants.EMPTY_STRING,
    var matches: List<Match> = emptyList(),
) {
    fun toAllMatches(): AllMatches {
        return AllMatches(
            id = id,
            matches = matches,
        )
    }
}
