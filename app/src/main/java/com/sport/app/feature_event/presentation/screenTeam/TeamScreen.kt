package com.sport.app.feature_event.presentation.screenTeam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sport.app.R
import com.sport.app.feature_event.presentation.common.LoadNoResultsView
import com.sport.app.feature_event.presentation.screenTeam.components.LoadTeamInfo
import com.sport.app.feature_event.presentation.screenTeam.viewmodel.TeamMatchesViewModel
import com.sport.app.feature_event.presentation.screenTeam.viewmodel.TeamViewModel

@Composable
fun TeamScreen(
    viewModel: TeamViewModel = hiltViewModel(),
    viewModelTeamMatches: TeamMatchesViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    val stateTeamMatches = viewModelTeamMatches.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = colorResource(id = R.color.primary))
        ) {
            for (team in state.value.teams) {
                if (stateTeamMatches.value.matches.isEmpty()) {
                    viewModelTeamMatches.getAllMatchesInTeam(team.id)
                }

                LoadTeamInfo(team, viewModelTeamMatches)
            }
        }

        if (state.value.error.isNotBlank()) {
            LoadNoResultsView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.primary)),
                imageSizeDp = dimensionResource(id = R.dimen.dp_180),
                noResultsText = stringResource(R.string.no_records_synced_yet)
            )
        }

        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}