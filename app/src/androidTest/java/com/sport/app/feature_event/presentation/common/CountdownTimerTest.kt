package com.sport.app.feature_event.presentation.common

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CountdownTimerTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun countDownMinuteExpectedText_assertExists() {
        val minuteInMs = 60000L
        testRule.setContent {
            CountdownTimer(minuteInMs)
        }
        val expectedText = "1m, 00s"
        testRule.onNodeWithText(expectedText).assertExists()
    }

    @Test
    fun countdownDaysMonthsMinutesSecondsText_assertExists() {
        val timeInMs = 16965052000 //196 days, 8 hours, 30 minutes and 52 seconds
        testRule.setContent {
            CountdownTimer(timeInMs)
        }
        val expectedText = "196d, 8h, 30m, 52s"
        testRule.onNodeWithText(expectedText).assertExists()
    }

    @Test
    fun countdownZeroTimeText_assertExists() {
        val zeroTimeInMs = 0L
        testRule.setContent {
            CountdownTimer(zeroTimeInMs)
        }
        val expectedText = "EVENT STARTED"
        testRule.onNodeWithText(expectedText).assertExists()
    }

}