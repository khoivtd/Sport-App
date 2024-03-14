package com.sport.app.feature_event.presentation.screenTeam.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sport.app.R
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.feature_event.domain.models.Team
import com.sport.app.feature_event.presentation.common.LoadNoResultsView
import com.sport.app.feature_event.presentation.common.LoadTeamMatches
import com.sport.app.feature_event.presentation.screenTeam.viewmodel.TeamMatchesViewModel
import com.sport.app.utils.Constants.DESCRIPTION_ICON
import com.sport.app.utils.Constants.EMPTY_STRING
import com.sport.app.utils.Constants.FLOAT_DEGREES_0
import com.sport.app.utils.Constants.FLOAT_DEGREES_180
import com.sport.app.utils.Constants.MAX_MATCHESS_PER_ROW
import com.sport.app.utils.Constants.SECTION_COLUMN_WEIGHT

@Composable
fun LoadTeamInfo(
    team: Team,
    viewModelTeamMatches: TeamMatchesViewModel = hiltViewModel(),
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) FLOAT_DEGREES_180 else FLOAT_DEGREES_0,
        label = EMPTY_STRING
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.clickable {
                expandedState = !expandedState
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = team.logo,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.dp_16))
                    .size(dimensionResource(id = R.dimen.dp_18)),
                contentDescription = DESCRIPTION_ICON,
            )
            Text(
                text = team.name,
                color = colorResource(id = R.color.onSecondary),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.dp_8),
                        vertical = dimensionResource(id = R.dimen.dp_8)
                    ),
                style = MaterialTheme.typography.titleMedium
            )
            Column(
                Modifier
                    .wrapContentHeight()
                    .weight(SECTION_COLUMN_WEIGHT, true),
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp_24))
                            .rotate(rotationState),
                        painter = painterResource(R.drawable.ic_caret_down),
                        contentDescription = DESCRIPTION_ICON,
                    )
                    Spacer(Modifier.width(dimensionResource(id = R.dimen.dp_8)))
                }
            }
        }
    }

    val state = viewModelTeamMatches.state.collectAsState()
    val teamMatches = state.value.matches

    if (expandedState && teamMatches.isNotEmpty()) {
        LoadMatchesInTeamSection(teamMatches)
    } else {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = dimensionResource(id = R.dimen.dp_16),
            color = colorResource(id = R.color.primary)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LoadMatchesInTeamSection(
    teamMatches: List<Match>
) {
    if (teamMatches.isNotEmpty()) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.primary))
                .padding(
                    top = dimensionResource(id = R.dimen.dp_16),
                    start = dimensionResource(id = R.dimen.dp_16),
                    end = dimensionResource(id = R.dimen.dp_16),
                    bottom = dimensionResource(id = R.dimen.dp_32),
                )
        ) {
            //using flow layout here instead of a Lazy Grid, so that the layout computation (composition) becomes smoother and without any nested hierarchies
            // such as lazy grid inside of a lazy list or vertically scrollable column
            androidx.compose.foundation.layout.FlowRow(
                modifier = Modifier.background(color = colorResource(id = R.color.primary)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_16)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_16)),
                maxItemsInEachRow = MAX_MATCHESS_PER_ROW
            ) {
                for (teamMatch in teamMatches) {
                    LoadTeamMatches(
                        teamMatch
                    )
                }

            }
        }
    } else {
        LoadNoResultsView(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.surface)),
            imageSizeDp = dimensionResource(id = R.dimen.dp_120),
            noResultsText = stringResource(R.string.no_upcoming_match_planned)
        )
    }
}
