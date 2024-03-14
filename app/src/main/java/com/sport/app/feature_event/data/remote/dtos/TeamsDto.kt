package com.sport.app.feature_event.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class TeamsDto(
    @SerializedName("teams")
    val teams: List<TeamDto>,
)
