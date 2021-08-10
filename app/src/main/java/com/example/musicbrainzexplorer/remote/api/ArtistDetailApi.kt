package com.example.musicbrainzexplorer.remote.api

import com.example.musicbrainzexplorer.remote.model.ArtistDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistDetailApi {
    @GET("artist/{id}?inc=release-groups")
    suspend fun artistDetail(@Path("id") id: String): ArtistDetailResponse
}