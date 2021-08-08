package com.example.musicbrainzexplorer.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicbrainzexplorer.remote.model.Artist
import com.example.musicbrainzexplorer.repository.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val artistsRepository: ArtistsRepository
) : ViewModel() {
    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    fun onClickSearch(query: String) {
        viewModelScope.launch {
            artistsRepository.searchArtists(query).collect {
                _artists.postValue(it)
            }
        }
    }
}
