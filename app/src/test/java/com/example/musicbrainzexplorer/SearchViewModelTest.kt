package com.example.musicbrainzexplorer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicbrainzexplorer.remote.model.Artist
import com.example.musicbrainzexplorer.repository.ArtistsRepository
import com.example.musicbrainzexplorer.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val artistsRepository: ArtistsRepository = mockk()
    private val artistsObserver: Observer<List<Artist>> = mockk(relaxUnitFun = true)

    private val viewModel = SearchViewModel(artistsRepository)

    @Before
    fun setUp() {
        viewModel.artists.observeForever(artistsObserver)
    }

    @Test
    fun `onClickSearch triggers call to artists repository and posts result to artists live data`() =
        runBlocking {
            val expectedQuery = "query"
            val expectedArtists: List<Artist> = mockk(relaxed = true)
            val artistsFlow: Flow<List<Artist>> = flow {
                emit(expectedArtists)
            }
            coEvery { artistsRepository.searchArtists(expectedQuery) } returns artistsFlow

            viewModel.onClickSearch(expectedQuery)

            coVerify { artistsRepository.searchArtists(expectedQuery) }
            verify { artistsObserver.onChanged(expectedArtists) }
        }
}