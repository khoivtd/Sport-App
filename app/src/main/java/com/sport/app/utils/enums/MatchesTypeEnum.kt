package com.sport.app.utils.enums

import com.sport.app.R

enum class MatchesTypeEnum(
    var matchesType: Int,
) {
    TYPE_PREVIOUS_MATCHES(
        matchesType = R.string.previous_matches,
    ),
    TYPE_UPCOMING_MATCHES(
        matchesType = R.string.upcoming_matches,
    ),
}
