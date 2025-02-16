package com.example.l66_unit_test_robolectric

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import com.example.l66_unit_test_robolectric.TestTags.CounterText
import com.example.l66_unit_test_robolectric.TestTags.IncrementButton
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class CounterTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() = with(composeTestRule) {
        setContent {
            Counter()
        }
    }

    @Test
    fun initialCounter_containsZeroValue(): Unit = with(composeTestRule) {
        onNodeWithTag(CounterText).assertTextEquals("0")
    }

    @Test
    fun clickIncrementButton_incrementsCounter(): Unit = with(composeTestRule) {
        onNodeWithTag(IncrementButton).performClick()

        onNodeWithTag(CounterText).assertTextEquals("1")
    }

}
