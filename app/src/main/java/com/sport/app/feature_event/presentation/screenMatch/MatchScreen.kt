package com.sport.app.feature_event.presentation.screenMatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.sport.app.feature_event.presentation.screenMatch.components.LoadMatchSection
import com.sport.app.feature_event.presentation.screenMatch.viewmodel.MatchViewModel

@Composable
fun MatchScreen(
    viewModel: MatchViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = colorResource(id = R.color.primary))
                .padding(bottom = dimensionResource(id = R.dimen.dp_32)),
        ) {
            LoadMatchSection(state.value.matches)
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