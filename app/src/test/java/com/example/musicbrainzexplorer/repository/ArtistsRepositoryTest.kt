package com.example.musicbrainzexplorer.repository

import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import com.example.musicbrainzexplorer.remote.model.Artist
import com.example.musicbrainzexplorer.remote.model.ArtistsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtistsRepositoryTest {

    private val searchArtistsApi: SearchArtistsApi = mockk()

    private val artistsRepository = ArtistsRepository(searchArtistsApi)

    @Test
    fun `calls searchArtistsApi with query and returns mapped response`() = runBlocking {
        val expectedQuery = "beatles"
        val expectedArtists = listOf(Artist(id = "123", name = "The Beatles"))
        coEvery { searchArtistsApi.searchArtist(expectedQuery) } returns ArtistsResponse(artists = expectedArtists)

        val artists = artistsRepository.searchArtists(expectedQuery)

        coVerify { searchArtistsApi.searchArtist(expectedQuery) }
        assertEquals(expectedArtists, artists)
    }
}