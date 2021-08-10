package com.example.musicbrainzexplorer.remote.api

import com.example.musicbrainzexplorer.remote.model.ArtistDetailResponse
import retrofit2.http.GET

interface ArtistDetailApi {
    @GET("artist/{id}?inc=release-groups")
    suspend fun artistDetail(): ArtistDetailResponse
}