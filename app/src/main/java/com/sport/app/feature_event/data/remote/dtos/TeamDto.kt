package com.sport.app.feature_event.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.sport.app.feature_event.data.cache.entity.TeamEntity

data class TeamDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
) {
    fun toTeamEntity(): TeamEntity {
        return TeamEntity(
            id = id,
            name = name,
            logo = logo,
        )
    }
}
