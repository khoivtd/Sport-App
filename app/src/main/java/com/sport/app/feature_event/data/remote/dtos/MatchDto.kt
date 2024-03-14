package com.sport.app.feature_event.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.sport.app.feature_event.data.cache.entity.MatchEntity

data class MatchDto(
    @SerializedName("description") val description: String,
    @SerializedName("home") val home: String,
    @SerializedName("away") val away: String,
    @SerializedName("winner") val winner: String,
    @SerializedName("date") val date: String,
    @SerializedName("highlights") val highlights: String,
) {
    fun toMatchEntity(isPreviousMatch: Boolean = false): MatchEntity {
        return MatchEntity(
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
