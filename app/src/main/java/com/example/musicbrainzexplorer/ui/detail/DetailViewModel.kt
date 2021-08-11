package com.example.musicbrainzexplorer.ui.detail

import androidx.lifecycle.*
import com.example.musicbrainzexplorer.remote.model.ArtistDetail
import com.example.musicbrainzexplorer.repository.ArtistDetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    @Assisted private val artistId: String,
    private val artistDetailRepository: ArtistDetailRepository
) : ViewModel() {
    private val _artistDetail = MutableLiveData<ArtistDetail>()
    val artistDetail: LiveData<ArtistDetail> = _artistDetail

    init {
        viewModelScope.launch {
            _artistDetail.value = artistDetailRepository.artistDetail(artistId)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(artistId: String): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            artistId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(p0: Class<T>) =
                assistedFactory.create(artistId) as T

        }
    }
}