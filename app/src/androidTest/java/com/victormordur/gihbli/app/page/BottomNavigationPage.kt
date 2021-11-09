package com.victormordur.gihbli.app.page

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.victormordur.gihbli.app.R
import com.victormordur.gihbli.app.presentation.list.FilmListActivity

abstract class BottomNavigationPage(
    protected val rule: AndroidComposeTestRule<ActivityScenarioRule<FilmListActivity>, FilmListActivity>
) {
    fun navigateToCatalogue(): CataloguePage {
        rule.onNodeWithText(
            rule.activity.resources.getString(R.string.catalogue),
            useUnmergedTree = true
        ).performClick()
        return CataloguePage(rule)
    }

    fun navigateToWatchList(): WatchListPage {
        rule.onNodeWithText(
            rule.activity.resources.getString(R.string.watch_list),
            useUnmergedTree = true
        ).performClick()
        return WatchListPage(rule)
    }

    fun navigateToWatched(): WatchedPage {
        rule.onNodeWithText(
            rule.activity.resources.getString(R.string.watched),
            useUnmergedTree = true
        ).performClick()
        return WatchedPage(rule)
    }
}
