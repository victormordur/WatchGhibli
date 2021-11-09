package com.victormordur.gihbli.app.page

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.list.FilmListActivity

class WatchListPage(
    rule: AndroidComposeTestRule<ActivityScenarioRule<FilmListActivity>, FilmListActivity>
) : BottomNavigationPage(rule) {

    fun assertIsEmpty(): WatchListPage {
        rule.onNodeWithText(
            rule.activity.resources.getString(R.string.watch_list_empty),
            useUnmergedTree = true
        ).assertIsDisplayed()
        return WatchListPage(rule)
    }
}
