package com.example.musicbrainzexplorer.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistsRepository @Inject constructor() {
    suspend fun searchArtists(query: String): Flow<List<Artist>> = flow {
        emit(emptyList())
    }
}

class Artist