package com.example.musicbrainzexplorer.ui.detail

import com.example.musicbrainzexplorer.di.FakeArtistDetailApi
import com.example.musicbrainzexplorer.launchFragmentInHiltContainer
import com.example.musicbrainzexplorer.remote.model.*
import com.example.musicbrainzexplorer.robots.DetailRobot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@HiltAndroidTest
class DetailFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val detailRobot = DetailRobot()

    @Test
    fun testDetailFragmentDisplaysInformation() {
        val name = "Some name"
        val status = Status.Ended(begin = "1976", end = "1977")
        val releaseGroups: List<ReleaseGroup> = listOf(
            ReleaseGroup(ReleaseType.Album, "Album", LocalDate.of(1976, 6, 24)),
            ReleaseGroup(ReleaseType.Other, "This is not displayed", null)
        )
        val area = Area(name = "Area name")
        FakeArtistDetailApi.response = ArtistDetail(
            name = name,
            type = ArtistType.Group,
            status = status,
            releaseGroups = releaseGroups,
            beginArea = area
        )

        val args = DetailFragmentArgs(artistId = "some-id").toBundle()
        launchFragmentInHiltContainer<DetailFragment>(args)

        detailRobot.checkActivityIsDisplayed()
        detailRobot.checkArtistNameMatchesText(name)
        detailRobot.checkAreaMatchesText(area.name)
        detailRobot.checkLifeSpanMatchesText(status.formatted())
        detailRobot.checkAlbumListIsDisplayedWithCount(1)
    }
}