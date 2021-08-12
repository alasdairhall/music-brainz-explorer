package com.example.musicbrainzexplorer.di

import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import com.example.musicbrainzexplorer.remote.model.ArtistsResponse

class FakeSearchArtistsApi : SearchArtistsApi {
    override suspend fun searchArtist(query: String): ArtistsResponse = response

    companion object {
        var response = ArtistsResponse(artists = listOf())
    }

}
