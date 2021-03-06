package com.example.musicbrainzexplorer.remote.api

import com.example.musicbrainzexplorer.remote.model.ArtistsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchArtistsApi {
    @GET("artist")
    suspend fun searchArtist(@Query("query") query: String): ArtistsResponse
}