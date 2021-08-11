package com.example.musicbrainzexplorer.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicbrainzexplorer.remote.model.ArtistDetail
import com.example.musicbrainzexplorer.repository.ArtistDetailRepository
import com.example.musicbrainzexplorer.testhelpers.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `emits artist detail on init`() {
        val artistDetailRepository: ArtistDetailRepository = mockk()
        val expectedArtistDetail: ArtistDetail = mockk()
        val artistId = "artist-id"
        coEvery { artistDetailRepository.artistDetail(artistId) } returns expectedArtistDetail

        val viewModel = DetailViewModel(artistId, artistDetailRepository)
        val observer: Observer<ArtistDetail> = mockk(relaxUnitFun = true)
        viewModel.artistDetail.observeForever(observer)

        coVerify { artistDetailRepository.artistDetail(artistId) }
        verify { observer.onChanged(expectedArtistDetail) }
    }
}