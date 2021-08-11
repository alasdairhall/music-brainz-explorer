package com.example.musicbrainzexplorer.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistDetail(
    val name: String,
    val type: ArtistType?,
    @Json(name = "life-span")
    val status: Status?,
    @Json(name = "release-groups")
    val releaseGroups: List<ReleaseGroup>,
    val area: Area?,
    @Json(name = "begin-area")
    val beginArea: Area?
) {
    val albums = releaseGroups.filter { it.primaryType == "Album" }
}

enum class ArtistType {
    Person,
    Group,
    Orchestra,
    Choir,
    Character,
    Other
}

@JsonClass(generateAdapter = true)
data class LifeSpanResponse(
    val begin: String?,
    val end: String?,
    val ended: Boolean? = false
)

sealed class Status {
    abstract fun formatted(): String

    data class Active(val begin: String) : Status() {
        override fun formatted() = "$begin -"
    }

    data class Ended(val begin: String, val end: String) : Status() {
        override fun formatted() = "$begin - $end"
    }
}

@JsonClass(generateAdapter = true)
data class ReleaseGroup(
    @Json(name = "primary-type")
    val primaryType: String?, // TODO enum
    val title: String,
    @Json(name = "first-release-date")
    val firstReleaseDate: String // TODO Date/LocalDate
)