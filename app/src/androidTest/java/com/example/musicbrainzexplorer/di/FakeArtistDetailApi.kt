package com.example.musicbrainzexplorer.di

import com.example.musicbrainzexplorer.remote.api.ArtistDetailApi
import com.example.musicbrainzexplorer.remote.model.Area
import com.example.musicbrainzexplorer.remote.model.ArtistDetail
import com.example.musicbrainzexplorer.remote.model.ArtistType
import com.example.musicbrainzexplorer.remote.model.Status

class FakeArtistDetailApi : ArtistDetailApi {
    override suspend fun artistDetail(id: String) = response

    companion object {
        var response = ArtistDetail(
            name = "The Beatles",
            type = ArtistType.Group,
            status = Status.Ended(begin = "1957", end = "1970"),
            releaseGroups = listOf(),
            beginArea = Area(name = "Liverpool")
        )
    }

}
