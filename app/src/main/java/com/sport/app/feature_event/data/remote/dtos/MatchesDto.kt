package com.sport.app.feature_event.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class MatchesDto(
    @SerializedName("matches")
    val matches: AllMatchesDto,
)
