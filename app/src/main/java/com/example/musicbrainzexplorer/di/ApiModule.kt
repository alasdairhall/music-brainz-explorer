package com.example.musicbrainzexplorer.di

import com.example.musicbrainzexplorer.remote.api.SearchArtistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideSearchArtistsApi(retrofit: Retrofit): SearchArtistsApi =
        retrofit.create(SearchArtistsApi::class.java)
}