package com.example.musicbrainzexplorer.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistDetailResponse(
    val name: String,
    val type: String?, // TODO enum
    @Json(name = "life-span")
    val lifeSpan: LifeSpanResponse?,
    @Json(name = "release-groups")
    val releaseGroups: List<ReleaseGroup>,
    val area: Area?,
    @Json(name = "begin-area")
    val beginArea: Area?
) {
    val albums = releaseGroups.filter { it.primaryType == "Album" }
}

@JsonClass(generateAdapter = true)
data class LifeSpanResponse(
    val begin: String?,
    val end: String?,
    val ended: Boolean? = false
)

@JsonClass(generateAdapter = true)
data class ReleaseGroup(
    @Json(name = "primary-type")
    val primaryType: String?, // TODO enum
    val title: String,
    @Json(name = "first-release-date")
    val firstReleaseDate: String // TODO Date/LocalDate
)