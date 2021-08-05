package com.example.musicbrainzexplorer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = SearchViewModel()

    @Test
    fun `onClickSearch triggers call to artists repository`() {

    }
}