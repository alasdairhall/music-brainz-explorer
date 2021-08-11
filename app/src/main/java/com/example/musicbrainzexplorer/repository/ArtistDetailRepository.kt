package com.example.musicbrainzexplorer.repository

import com.example.musicbrainzexplorer.remote.api.ArtistDetailApi
import com.example.musicbrainzexplorer.remote.model.ArtistDetail
import javax.inject.Inject


class ArtistDetailRepository @Inject constructor(
    private val artistDetailApi: ArtistDetailApi
) {
    suspend fun artistDetail(id: String): ArtistDetail = artistDetailApi.artistDetail(id)
}
