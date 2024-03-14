package com.sport.app.feature_event.domain.models

import com.sport.app.utils.Constants.EMPTY_STRING

data class Team(
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val logo: String = EMPTY_STRING,
)
