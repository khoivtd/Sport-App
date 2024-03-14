package com.sport.app.feature_event.domain.models

data class AllMatches(
    val id: String,
    val matches: List<Match> = emptyList(),
)
