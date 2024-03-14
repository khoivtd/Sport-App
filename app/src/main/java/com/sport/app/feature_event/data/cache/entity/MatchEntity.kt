package com.sport.app.feature_event.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.utils.Constants

@Entity
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var description: String? = Constants.EMPTY_STRING,
    var home: String? = Constants.EMPTY_STRING,
    var away: String? = Constants.EMPTY_STRING,
    var winner: String? = Constants.EMPTY_STRING,
    var date: String? = Constants.EMPTY_STRING,
    var highlights: String? = Constants.EMPTY_STRING,
    var isPreviousMatch: Boolean = false,
) {
    fun toMatch(): Match {
        return Match(
            description = description,
            home = home,
            away = away,
            winner = winner,
            date = date,
            highlights = highlights,
            isPreviousMatch = isPreviousMatch,
        )
    }
}
