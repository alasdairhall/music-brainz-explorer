package com.example.musicbrainzexplorer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicbrainzexplorer.remote.model.Artist
import com.example.musicbrainzexplorer.repository.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {
    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    suspend fun onClickSearch(query: String) {
        artistsRepository.searchArtists(query).collect {
            _artists.postValue(it)
        }
    }
}
