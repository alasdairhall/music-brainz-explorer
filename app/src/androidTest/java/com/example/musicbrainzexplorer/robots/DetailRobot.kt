package com.example.musicbrainzexplorer.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.musicbrainzexplorer.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

class DetailRobot {
    fun checkActivityIsDisplayed() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
    }

    fun checkArtistNameMatchesText(text: String) {
        onView(withId(R.id.title)).check(matches(withText(text)))
    }

    fun checkAreaMatchesText(text: String) {
        onView(withId(R.id.area)).check(matches(withText(text)))
    }

    fun checkLifeSpanMatchesText(text: String) {
        onView(withId(R.id.lifeSpan)).check(matches(withText(text)))
    }

    fun checkAlbumListIsDisplayedWithCount(count: Int) {
        onView(withId(R.id.albumsList)).check(
            matches(
                allOf(
                    isDisplayed(),
                    hasChildCount(count)
                )
            )
        )
    }

    fun checkLifeSpanIsNotDisplayed() {
        onView(withId(R.id.lifeSpan)).check(matches(not(isDisplayed())))
    }

    fun checkAreaIsNotDisplayed() {
        onView(withId(R.id.area)).check(matches(not(isDisplayed())))
    }
}