package com.sport.app.feature_event.presentation.common


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.analytics.AnalyticsListener
import com.sport.app.R
import com.sport.app.feature_event.domain.models.Match
import com.sport.app.utils.Constants.DESCRIPTION_ICON
import io.sanghun.compose.video.RepeatMode
import io.sanghun.compose.video.VideoPlayer
import io.sanghun.compose.video.controller.VideoPlayerControllerConfig
import io.sanghun.compose.video.toRepeatMode
import io.sanghun.compose.video.uri.VideoPlayerMediaItem
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun LoadTeamMatches(
    match: Match,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimensionResource(id = R.dimen.dp_8))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dp_8))
        ) {

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("dd MMM yyyy HH:mm")

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_football),
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dp_4))
                            .size(dimensionResource(id = R.dimen.dp_24)),
                        contentDescription = DESCRIPTION_ICON,
                    )

                    match.date?.let {
                        val formattedDate = parser.parse(it)?.let { it1 -> formatter.format(it1) }
                        if (formattedDate != null) {
                            Text(
                                text = formattedDate,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelMedium,
                                color = colorResource(id = R.color.onSecondary),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(
                                        horizontal = dimensionResource(id = R.dimen.dp_8),
                                        vertical = dimensionResource(id = R.dimen.dp_8)
                                    ),
                            )
                        }
                    }
                }

                if (!match.isPreviousMatch) {
                    match.date?.let {
                        val dateTime = parser.parse(it)?.time
                        if (dateTime != null) {
                            CountdownTimer((dateTime) - System.currentTimeMillis())
                        }
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                match.home?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.onSecondary),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.dp_8),
                                vertical = dimensionResource(id = R.dimen.dp_8)
                            ),
                    )
                }

                Text(
                    text = stringResource(R.string.vs),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.tertiary),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.dp_8),
                            vertical = dimensionResource(id = R.dimen.dp_8)
                        ),
                )

                match.away?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorResource(id = R.color.onSecondary),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.dp_8),
                                vertical = dimensionResource(id = R.dimen.dp_8)
                            ),
                    )
                }
            }
        }

        if (!match.highlights.isNullOrEmpty()) {
            Surface(
                color = MaterialTheme.colorScheme.background,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.dp_200)),
                ) {
                    var repeatMode by remember { mutableStateOf(RepeatMode.NONE) }

                    VideoPlayer(
                        mediaItems = listOf(
                            VideoPlayerMediaItem.NetworkMediaItem(
                                url = match.highlights,
                                mediaMetadata = MediaMetadata.Builder()
                                    .setTitle(match.description).build(),
                                mimeType = MimeTypes.VIDEO_MP4,
                            )
                        ),
                        handleLifecycle = false,
                        autoPlay = false,
                        usePlayerController = true,
                        controllerConfig = VideoPlayerControllerConfig.Default.copy(
                            showSubtitleButton = true,
                            showNextTrackButton = true,
                            showBackTrackButton = true,
                            showBackwardIncrementButton = true,
                            showForwardIncrementButton = true,
                            showRepeatModeButton = true,
                            showFullScreenButton = true,
                        ),
                        repeatMode = repeatMode,
                        onCurrentTimeChanged = {
                            Log.e("CurrentTime", it.toString())
                        },
                        playerInstance = {
                            addAnalyticsListener(object : AnalyticsListener {
                                @SuppressLint("UnsafeOptInUsageError")
                                override fun onRepeatModeChanged(
                                    eventTime: AnalyticsListener.EventTime,
                                    rMode: Int,
                                ) {
                                    repeatMode = rMode.toRepeatMode()
                                }

                                @SuppressLint("UnsafeOptInUsageError")
                                override fun onPlayWhenReadyChanged(
                                    eventTime: AnalyticsListener.EventTime,
                                    playWhenReady: Boolean,
                                    reason: Int,
                                ) {
                                }

                                @SuppressLint("UnsafeOptInUsageError")
                                override fun onVolumeChanged(
                                    eventTime: AnalyticsListener.EventTime,
                                    volume: Float,
                                ) {
                                }
                            })
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                    )
                }
            }
        }
    }
}