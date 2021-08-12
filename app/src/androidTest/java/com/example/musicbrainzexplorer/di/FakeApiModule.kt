package com.example.musicbrainzexplorer.di

import com.example.musicbrainzexplorer.remote.api.ArtistDetailApi
import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ApiModule::class]
)
object FakeApiModule {
    @Provides
    @Singleton
    fun provideSearchArtistsApi(): SearchArtistsApi = FakeSearchArtistsApi()

    @Provides
    @Singleton
    fun provideArtistDetailApi(): ArtistDetailApi = FakeArtistDetailApi()
}