package com.sport.app.feature_event.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sport.app.feature_event.domain.models.Team
import com.sport.app.utils.Constants
import com.sport.app.utils.Constants.EMPTY_STRING

@Entity
data class TeamEntity(
    @PrimaryKey
    var id: String,
    var name: String = EMPTY_STRING,
    var logo: String = EMPTY_STRING,
) {
    fun toTeam(): Team {
        return Team(
            id = id,
            name = name,
            logo = logo,
        )
    }

    private fun Long.isDurationLessThanDay(): Boolean {
        val duration = (System.currentTimeMillis() / Constants.MILLIS_IN_SECOND) - this
        return duration <= Constants.SECONDS_IN_DAY
    }
}
