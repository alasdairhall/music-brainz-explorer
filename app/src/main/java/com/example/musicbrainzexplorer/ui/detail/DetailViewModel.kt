package com.example.musicbrainzexplorer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicbrainzexplorer.remote.model.ArtistDetailResponse
import com.example.musicbrainzexplorer.repository.ArtistDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val artistDetailRepository: ArtistDetailRepository
) : ViewModel() {
    //    val artistDetail: LiveData<ArtistDetailResponse> = artistDetailRepository.artistDetail("id").asLiveData()
    private val _artistDetail = MutableLiveData<ArtistDetailResponse>()
    val artistDetail: LiveData<ArtistDetailResponse> = _artistDetail

    init {
        viewModelScope.launch {
            artistDetailRepository.artistDetail("id").collect {
                _artistDetail.postValue(it)
            }
        }
    }
}