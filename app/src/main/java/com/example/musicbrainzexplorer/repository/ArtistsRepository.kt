package com.example.musicbrainzexplorer.repository

import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import com.example.musicbrainzexplorer.remote.model.Artist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistsRepository @Inject constructor(
    private val searchArtistsApi: SearchArtistsApi
) {
    suspend fun searchArtists(query: String): Flow<List<Artist>> = flow {
        emit(searchArtistsApi.searchArtist(query).artists)
    }
}