package com.sport.app.feature_event.domain.models

import com.sport.app.utils.Constants.EMPTY_STRING

data class Match(
    val date: String? = EMPTY_STRING,
    val description: String? = EMPTY_STRING,
    val home: String? = EMPTY_STRING,
    val away: String? = EMPTY_STRING,
    val winner: String? = EMPTY_STRING,
    val highlights: String? = EMPTY_STRING,
    val isPreviousMatch: Boolean = false,
)
