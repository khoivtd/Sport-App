package com.sport.app.feature_event.presentation.navigation

import com.sport.app.R
import com.sport.app.utils.Constants.ROUTE_MATCHES
import com.sport.app.utils.Constants.ROUTE_TEAMS

sealed class Screen(
    val labelResId: Int,
    val route: String,
    val icon: Int
) {
    object TeamScreen: Screen(
        labelResId = R.string.teams,
        route = ROUTE_TEAMS,
        icon = R.drawable.ic_football
    )

    object MatchScreen: Screen(
        labelResId = R.string.matches,
        route = ROUTE_MATCHES,
        icon = R.drawable.ic_trophy
    )
}
