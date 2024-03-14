package com.sport.app.feature_event.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.sport.app.R
import com.sport.app.utils.Constants.DESCRIPTION_IMAGE

/**
 * A composable function to display a "No Results" view.
 *
 * @param modifier          The modifier for this composable.
 * @param imageSizeDp       The image size of this composable in DP.
 * @param noResultsText     The text to display as a message when there are no results.
 */
@Composable
fun LoadNoResultsView(
    modifier: Modifier,
    imageSizeDp: Dp,
    noResultsText: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dp_8))
                .background(color = colorResource(id = R.color.surface))
                .size(imageSizeDp),
            contentDescription = DESCRIPTION_IMAGE,
            painter = painterResource(R.drawable.no_results_placeholder),
        )
        Text(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dp_16), vertical = dimensionResource(id = R.dimen.dp_8)),
            text = noResultsText,
            color = colorResource(id = R.color.secondary),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}