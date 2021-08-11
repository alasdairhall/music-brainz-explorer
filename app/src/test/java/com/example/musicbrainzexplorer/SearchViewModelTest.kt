package com.example.musicbrainzexplorer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicbrainzexplorer.remote.model.Artist
import com.example.musicbrainzexplorer.repository.ArtistsRepository
import com.example.musicbrainzexplorer.ui.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val artistsRepository: ArtistsRepository = mockk()
    private val artistsObserver: Observer<List<Artist>> = mockk(relaxUnitFun = true)

    private val viewModel = SearchViewModel(artistsRepository)

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel.artists.observeForever(artistsObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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