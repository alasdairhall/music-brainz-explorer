package com.example.musicbrainzexplorer.repository

import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import com.example.musicbrainzexplorer.remote.model.Artist
import javax.inject.Inject

class ArtistsRepository @Inject constructor(
    private val searchArtistsApi: SearchArtistsApi
) {
    suspend fun searchArtists(query: String): List<Artist> =
        searchArtistsApi.searchArtist(query).artists
}