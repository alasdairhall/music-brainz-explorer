package com.example.musicbrainzexplorer.di

import com.example.musicbrainzexplorer.remote.api.ArtistDetailApi
import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideSearchArtistsApi(retrofit: Retrofit): SearchArtistsApi =
        retrofit.create(SearchArtistsApi::class.java)

    @Provides
    @Singleton
    fun provideArtistDetailApi(retrofit: Retrofit): ArtistDetailApi =
        retrofit.create(ArtistDetailApi::class.java)
}