package com.sport.app.feature_event.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class AllMatchesDto(
    @SerializedName("previous")
    val previousMatches: List<MatchDto> = emptyList(),
    @SerializedName("upcoming")
    val upcomingMatches: List<MatchDto> = emptyList(),
)
