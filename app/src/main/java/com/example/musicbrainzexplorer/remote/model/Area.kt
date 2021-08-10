package com.example.musicbrainzexplorer.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Area(
    val name: String
)