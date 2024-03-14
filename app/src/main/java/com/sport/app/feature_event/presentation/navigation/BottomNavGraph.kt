package com.sport.app.feature_event.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sport.app.R
import com.sport.app.feature_event.presentation.screenMatch.MatchScreen
import com.sport.app.feature_event.presentation.screenTeam.TeamScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier
            .padding(
                bottom = dimensionResource(id = R.dimen.dp_68),
            )
            .background(color = colorResource(id = R.color.primary)),
        navController = navController,
        startDestination = Screen.TeamScreen.route,
    ) {
        composable(route = Screen.TeamScreen.route) {
            TeamScreen()
        }
        composable(route = Screen.MatchScreen.route) {
            MatchScreen()
        }
    }
}