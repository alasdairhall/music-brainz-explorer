package com.example.musicbrainzexplorer.remote.model.adapters

import com.example.musicbrainzexplorer.remote.model.ReleaseType
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ReleaseTypeAdapter {
    @FromJson
    fun fromJson(typeString: String): ReleaseType =
        ReleaseType.values().firstOrNull { it.toString() == typeString } ?: ReleaseType.Other

    @ToJson
    fun toJson(releaseType: ReleaseType): String = releaseType.toString()
}