package com.example.musicbrainzexplorer.repository

import com.example.musicbrainzexplorer.remote.api.ArtistDetailApi
import com.example.musicbrainzexplorer.remote.model.ArtistDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ArtistDetailRepository @Inject constructor(
    private val artistDetailApi: ArtistDetailApi
) {
    suspend fun artistDetail(id: String): Flow<ArtistDetailResponse> = flow {
        emit(artistDetailApi.artistDetail(id))
    }
}
