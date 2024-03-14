package com.sport.app.feature_event.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.sport.app.R
import com.sport.app.utils.Constants.EMPTY_STRING
import com.sport.app.utils.Constants.MILLIS_IN_SECOND
import com.sport.app.utils.Constants.MINUTES_IN_HOUR
import com.sport.app.utils.Constants.ONE_SECOND_DELAY
import com.sport.app.utils.Constants.SECONDS_IN_DAY
import com.sport.app.utils.Constants.SECONDS_IN_HOUR
import com.sport.app.utils.Constants.SECONDS_IN_MINUTE
import com.sport.app.utils.Constants.VALUE_ZERO
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(timeUntilEventStartInMs: Long) {
    var timeLeft by remember { mutableLongStateOf(timeUntilEventStartInMs) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > VALUE_ZERO) {
            delay(ONE_SECOND_DELAY)
            timeLeft -= MILLIS_IN_SECOND
        }
    }

    val seconds = (timeLeft / MILLIS_IN_SECOND).toInt()
    val daysLeft = seconds / SECONDS_IN_DAY
    val hoursLeft = (seconds % SECONDS_IN_DAY) / SECONDS_IN_HOUR
    val minutesLeft = (seconds % SECONDS_IN_HOUR) / MINUTES_IN_HOUR
    val secondsLeft = seconds % SECONDS_IN_MINUTE

    val countdownValue = if (timeLeft > VALUE_ZERO) {
        val daysString = if (daysLeft > VALUE_ZERO) "${daysLeft}d, " else EMPTY_STRING
        val hoursString = if (hoursLeft > VALUE_ZERO) "${hoursLeft}h, " else EMPTY_STRING
        val minutesString = if (minutesLeft > VALUE_ZERO) "${minutesLeft}m, " else EMPTY_STRING

        String.format("$daysString$hoursString$minutesString%02ds", secondsLeft)
    } else {
        stringResource(R.string.upcoming_match_started)
    }

    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    dimensionResource(id = R.dimen.dp_1),
                    if (timeLeft > VALUE_ZERO) colorResource(id = R.color.primary) else Color.Transparent
                ), shape = RectangleShape
            )
            .background(
                color = if (timeLeft > VALUE_ZERO) colorResource(id = R.color.onSurface) else colorResource(
                    id = R.color.tertiary
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dp_4)),
            text = countdownValue,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            color = if (timeLeft > VALUE_ZERO) colorResource(id = R.color.onSecondary) else colorResource(
                id = R.color.secondary
            ),
        )
    }
}